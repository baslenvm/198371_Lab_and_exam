<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/companies">
        <html>
            <head>
                <title>XSLT Lab : Problem 1.1</title>
            </head>
            <body>
                <xsl:element name="ul">
                    <xsl:for-each select="company">
                        
                        <xsl:element name="li">
                            <xsl:value-of select="name"/>
                        </xsl:element>
                            
                    </xsl:for-each>
                    
                </xsl:element>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
