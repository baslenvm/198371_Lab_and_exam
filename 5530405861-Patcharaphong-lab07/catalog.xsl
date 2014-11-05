<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/catalog">
<html>
<body>
<h2>My CD Collection</h2>
<table border="0">
<tr bgcolor="#9acd32">
<th 
align="left">Title</th>
<th 
align="left">Artist</th>
</tr>
<xsl:for-each 
select="cd">
<tr>
<td><xsl:value-of 
select="title"/></td>
<td><xsl:value-of 
select="artist"/></td>
</tr>
</xsl:for-each>
</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>
