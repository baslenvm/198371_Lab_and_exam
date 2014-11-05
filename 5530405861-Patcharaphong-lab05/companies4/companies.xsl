<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:template match="/companies">
        <xsl:element name="companies">
            <xsl:for-each select="company">  
                <xsl:element name="company">
                    <xsl:attribute name="symbol">
                        <xsl:value-of select="symbol"/>
                    </xsl:attribute>
                    <xsl:element name="name">
                        <xsl:value-of select="name"/>
                    </xsl:element>
                    <xsl:apply-templates select="research"/>
                    <xsl:if test="symbol = 'IBM'" >
                        <xsl:element name="lab">
                            <xsl:attribute name="city">
                                <xsl:text>austin</xsl:text>
                            </xsl:attribute>
                            <xsl:text>IBM Austin Research Lab</xsl:text>
                        </xsl:element>
                    </xsl:if>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="research">
        
        <xsl:for-each select="labs/lab">
            <xsl:element name="lab">
                <xsl:attribute name="city">
                    <xsl:value-of select="@location"/>
                </xsl:attribute>
                <xsl:value-of select="."/>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>

