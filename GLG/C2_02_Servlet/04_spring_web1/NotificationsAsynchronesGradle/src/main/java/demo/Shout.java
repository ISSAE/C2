package demo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rosmord
 */
@WebServlet(urlPatterns = {"/shout"}, asyncSupported = true)
public class Shout extends HttpServlet {

    private final List<AsyncContext> contexts = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.err.println("Création d'un contexte " + contexts.size());
        // On crée un contexte pour répondre à cette question.
        final AsyncContext asyncContext = request.startAsync(request, response);
        //asyncContext.setTimeout(10 * 60 * 1000);
        asyncContext.setTimeout(0); // never die ???
        synchronized (contexts) {
            contexts.add(asyncContext);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.err.println("Reception des données");
        String htmlMessage = preparerReponse(request);
        List<AsyncContext> currentContexts;
        synchronized (contexts) {
            System.err.println("Récupération des contextes pour envoi de données");
            currentContexts = new ArrayList<>(this.contexts);
            System.err.println("Mise à zéro des contextes.");
            this.contexts.clear();
        }
        System.err.println("On envoi à tous les contextes les modifications");
        for (AsyncContext asyncContext : currentContexts) {
            try {
                // voir note (A) en fin de classe.
                PrintWriter writer = asyncContext.getResponse().getWriter();
                writer.println(htmlMessage);
                writer.flush();                
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        }
    }

    private String preparerReponse(HttpServletRequest request) {
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        String htmlMessage = "<p><b>" + name + "</b><br/>" + message + "</p>";
        ServletContext sc = request.getServletContext();
        if (sc.getAttribute("messages") == null) {
            sc.setAttribute("messages", htmlMessage);
        } else {
            String currentMessages = (String) sc.getAttribute("messages");
            sc.setAttribute("messages", htmlMessage + currentMessages);
        }
        return htmlMessage;
    }

    // Note A: petit détail : nous avions dans un premier temps utilisé 
    // un try-with-resources pour créer le PrintWriter. Mais il était alors 
    // fermé deux fois : une fois par le try-with-resources, et une fois par 
    // asyncContext.complete(), causant une exception. Pour éviter celle-ci,
    // nous sommes revenus à du code plus "standard". 
}
