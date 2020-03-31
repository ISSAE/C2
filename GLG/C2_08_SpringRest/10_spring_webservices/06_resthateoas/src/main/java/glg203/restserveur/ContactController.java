package glg203.restserveur;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import glg203.rest.dto.ContactDTO;
import glg203.rest.modele.Contact;

/**
 * ContactController.
 * <p>
 * Nota : il faudrait proposer un service pour faire l'interface entre le métier
 * et cette couche, mais en plaçant le code (qui n'est pas trop long) ici, on
 * simplifie la compréhension de hateoas.
 */
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService service;

    @GetMapping
    public PagedModel<ContactDTO> listContacts(@RequestParam(value = "page", defaultValue = "0") int page) {
        List<ContactDTO> all = service.findAll().stream().map(this::contact2ContactDTO).collect(Collectors.toList());
        // Pagination...
        List<ContactDTO> subList;
        int startIndex = page * 10;
        int endIndex = Math.min(page * 10 + 10, all.size());
        if (startIndex < endIndex)
            subList = all.subList(startIndex, endIndex);
        else
            subList = Collections.emptyList();

        List<Link> links = new ArrayList<>();

        if (endIndex < all.size()) {
            Link nextLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ContactController.class).listContacts(page+1))
                    .withRel(IanaLinkRelations.NEXT);
            links.add(nextLink);
        }

        if (startIndex > 0) {
            Link prevLink = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(ContactController.class).listContacts(page-1))
                    .withRel(IanaLinkRelations.PREVIOUS);
            links.add(prevLink);
        }     
        PagedModel<ContactDTO> pagedModel = new PagedModel<ContactDTO>(subList,
                new PagedModel.PageMetadata(10l, page, all.size()), links);
        return pagedModel;
    }

    @GetMapping("/{id}")
    public ContactDTO get(@PathVariable final Long id) {
        return contact2ContactDTO(service.getContact(id));
    }

    @GetMapping("/{id}/available")
    public Boolean isAvailable(@PathVariable final Long id) {
        return service.isAvailable(id);
    }

    @PostMapping
    @ResponseBody
    public ContactDTO post(@RequestBody final ContactDTO contact) {
        final Contact c = contactDTO2Contact(contact);
        service.addContact(c);
        return contact2ContactDTO(c);
    }

    private ContactDTO contact2ContactDTO(final Contact c) {
        try {
            final ContactDTO res = new ContactDTO(c);
            // à la main... à éviter
            // res.add(new Link("http://localhost:8080/api/contact/"+c.getId(), "self"));
            // res.add(new Link("http://localhost:8080/api/contact/"+c.getId()+ "available", "available"));
            // On ajoute le lien "self"
            res.add(WebMvcLinkBuilder.linkTo(ContactController.class).slash(c.getId()).withSelfRel());
            // On ajoute un lien vers la relation "available"
            // Version 1 : introspection
            Method availableMethod;

            availableMethod = ContactController.class.getMethod("isAvailable", Long.class);
            res.add(WebMvcLinkBuilder.linkTo(availableMethod, c.getId()).withRel("available"));
            // Méthode 2
            // Link link =
            // WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContactController.class).isAvailable(c.getId()))
            // .withRel("available");
            // res.add(link);
            // Ne fonctionne pas ici, car Boolean n'est pas manipulable par Spring.
            return res;
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
    // new Link("/some-resource"), IanaLinkRelations.NEXT);

    private Contact contactDTO2Contact(final ContactDTO c0) {
        final Contact c = new Contact(c0.getNom(), c0.getTelephone());
        return c;
    }

}