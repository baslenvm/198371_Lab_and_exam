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
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="research">
        
        <xsl:for-each select="labs/lab">
            <xsl:element name="lab">
                <xsl:apply-templates select="@location"/>
                <xsl:value-of select="."/>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="@location">
        <xsl:choose>
            <xsl:when test=". = 'new york city'">
                <xsl:attribute name="state">
                    <xsl:text>New York</xsl:text>
                </xsl:attribute>
            </xsl:when>
            <xsl:when test=". = 'san jose'">
                <xsl:attribute name="state">
                    <xsl:text>California</xsl:text>
                </xsl:attribute>
            </xsl:when>
            <xsl:when test=". = 'pittsburgh'">
                <xsl:attribute name="state">
                    <xsl:text>Pennsylvania</xsl:text>
                </xsl:attribute>
            </xsl:when>
            <xsl:otherwise />
        </xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>