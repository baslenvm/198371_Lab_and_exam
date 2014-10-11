<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : teacher.xsl
    Created on : October 10, 2014, 3:05 PM
    Author     : Patcharaphong
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:en='http://gear.kku.ac.th'>
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>198371 Midterm: XMLT</title>
            </head>
            <body>
                <h2>198371 Midterm: XMLT</h2>
                <h3>By Patcharaphong Batdee</h3>
                <xsl:for-each select="en:teachers/en:teacher" >
                    <xsl:apply-templates select="en:name" /> teaches
                    <ul>
                        <xsl:for-each select="en:classes/en:class" >
                            <li>
                                <xsl:value-of select="." />
                                <br />
                            </li>
                        </xsl:for-each>
                    </ul>
               
                </xsl:for-each>
                <xsl:for-each select="en:teachers/en:teacher" >
                    <xsl:if test="en:papers != ''">
                        <xsl:apply-templates select="en:name" /> publishes
                        <ul>
                            <xsl:for-each select="en:papers/en:paper" >
                                <li>
                                    <xsl:value-of select="@name" />
                                    <br />
                                </li>
                            </xsl:for-each>
                        </ul>
                    </xsl:if>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="en:name">
        <xsl:value-of select="text()" />
    </xsl:template>
</xsl:stylesheet>
