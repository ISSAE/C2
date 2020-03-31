<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
  
    <xsl:output method="html"/>

    <!-- 
    règle à appliquer au document dans son ensemble :
    on commence par générer l'en-tête, puis une table des
    matières, puis on met en forme le corps du document
    -->

    <xsl:template match="mondocument">
        <html>
            <header>
                <title></title>
            </header>
            <body>
                <!-- la table des matières -->
                <ul>
                    <xsl:for-each select="chapitre/titre">
                        <li> 
                            <a href="#{generate-id(..)}">
                                <xsl:number count="chapitre"/>
                                <xsl:text>. </xsl:text>
                                <xsl:value-of select="."/>
                            </a>
                        </li>
                    </xsl:for-each>
                </ul>
                <!-- le corps du document -->
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <!-- On transforme les titres en titres html,
       en créant un identificateur pour la table des matières
    -->
    <xsl:template match="chapitre/titre">
        <h2 id="{generate-id(..)}">
            <xsl:number count="chapitre"/> 
            <xsl:text>. </xsl:text>
            <xsl:apply-templates/>  
        </h2>
    </xsl:template>

    <xsl:template match="mondocument/titre">
        <h1>     
            <xsl:apply-templates/>  
        </h1>
    </xsl:template>
    <!-- Les commentaires ne sont pas affichés : -->
    <!-- Sans cette règle, leur texte serait recopié tel quel. -->
    <xsl:template match="commentaire">
    </xsl:template>
</xsl:stylesheet>
