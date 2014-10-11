<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : books.xsl
    Created on : September 24, 2014, 4:57 PM
    Author     : Patcharaphong
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:coe="http://gear.kku.ac.th/Patcharaphong">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Patcharaphong Batdee's Books</title>
            </head>
            <body>
                <h2>My Booka Collection</h2>
                <table border="1">
                    <tr brcolor="#9acb32">
                        <th align="lieft">
                            Title
                        </th>
                        <th align="lieft">
                            Price
                        </th>
                    </tr>
                    <xsl:for-each select="coe:catalog/coe:book">
                        <tr>
                            <td>
                                <xsl:value-of select="coe:title" />
                            </td>
                            <td>
                                <xsl:value-of select="coe:price" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
