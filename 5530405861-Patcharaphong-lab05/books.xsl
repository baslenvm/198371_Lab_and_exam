<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="rdf rss"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:lib="http://www.zvon.org/library">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <xsl:element name='html'>
            <xsl:element name='head'>
            </xsl:element>
            <xsl:element name='body'>
                <xsl:element name='table'>
                    <xsl:attribute name='border'>
                        <xsl:text>1</xsl:text>
                    </xsl:attribute>
                    <xsl:element name='tr'>
                        <xsl:element name='th'>
                            <xsl:text>Title</xsl:text>
                        </xsl:element>
                        <xsl:element name='th'>
                            <xsl:text>Pages</xsl:text>
                        </xsl:element>
                    </xsl:element>
                    <xsl:for-each select='rdf:RDF/rdf:Description'>
                        <xsl:sort select='lib:pages' data-type="number" order="ascending"/>
                        <xsl:if test="rdf:type/@resource = 'http://www.zvon.org/library/Book'">
                            <xsl:element name='tr'>
                                <xsl:element name='td'>
                                    <xsl:text>
                                        <xsl:value-of select="@about" />
                                    </xsl:text>
                                </xsl:element>
                                <xsl:element name='td'>
                                    <xsl:text>
                                        <xsl:number value="lib:pages" /> 
                                    </xsl:text>
                                </xsl:element>
                            </xsl:element>
                        </xsl:if>
                    </xsl:for-each>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
