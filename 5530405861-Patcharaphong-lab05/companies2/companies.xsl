<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/companies">
        <html>
            <head>
                <title>XSLT Lab : Problem 1.2</title>
            </head>
            <body>
                <xsl:element name="h2">
                    <xsl:text>These are the leading companies:</xsl:text>
                </xsl:element>
                <xsl:element name="ul">
                    <xsl:for-each select="company">                   
                        <xsl:element name="li">
                            <xsl:value-of select="name"/>
                        </xsl:element>
                        <xsl:element name="br"/>
                        <xsl:apply-templates select="research"/>
                    </xsl:for-each>
                    
                </xsl:element>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="research">
        <xsl:element name="ul">
        <xsl:for-each select="labs/lab">
            <xsl:element name="li">
                <xsl:value-of select="."/>
            </xsl:element>
        </xsl:for-each>
    </xsl:element>
    </xsl:template>
</xsl:stylesheet>
