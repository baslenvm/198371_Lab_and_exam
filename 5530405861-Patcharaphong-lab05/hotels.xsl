<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom" >
    <xsl:output method="html" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:element name='html'>
            <xsl:element name='head'>
            </xsl:element>
            <xsl:element name='body'>
                <xsl:element name='h1'>
                    <xsl:element name='img'>
                        <xsl:attribute name='src'>
                            <xsl:value-of select='kml:kml/kml:Document/kml:Style/kml:IconStyle/kml:Icon/kml:href' />
                        </xsl:attribute>
                    </xsl:element>
                    <xsl:text>
                        <xsl:value-of select='kml:kml/kml:Document/kml:name' />
                    </xsl:text>
                </xsl:element>
                <xsl:text>List of hotels</xsl:text>
                <xsl:apply-templates select='kml:kml/kml:Document'/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match='/kml:kml/kml:Document'>
        <xsl:element name='ul'>
            <xsl:for-each select='kml:Placemark'>
                <xsl:sort select='kml:name'/>
                <xsl:element name='li'>
                    <xsl:text>
                        <xsl:value-of select='kml:name' />
                    </xsl:text>
                </xsl:element>
                <xsl:element name='ul'>
                    <xsl:element name='li'>
                        <xsl:element name='a'>
                            <xsl:attribute name="href">
                                <xsl:value-of select="substring-before(substring-after(kml:description, '&lt;a href=&quot;'), '&quot;&gt;&lt;img')" />
                            </xsl:attribute>
                            <xsl:text>
                                <xsl:value-of select="substring-before(substring-after(kml:description, '&lt;a href=&quot;'), '&quot;&gt;&lt;img')" />
                            </xsl:text>
                        </xsl:element>
                    </xsl:element>
                    <xsl:element name='li'>
                        <xsl:text>
                            Coordinates:<xsl:value-of select="kml:Point/kml:coordinates" />
                        </xsl:text>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
