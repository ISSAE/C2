<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>   
    <xsl:template match="*">
        <xsl:copy>
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="auteur">
        <nomAuteur>
            <xsl:value-of select="text()"/>
        </nomAuteur>
    </xsl:template>
    
</xsl:stylesheet>
