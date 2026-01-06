<%
def entry = item ?: [:]
def toHtmlPath = { p ->
    if (!p) return ''
    def pathStr = p.toString()
    if (!pathStr.toLowerCase().endsWith('.html')) {
        pathStr = pathStr.replaceFirst(/\.[^\/]+$/, '')
        pathStr = "${pathStr}.html"
    }
    baseUrl ? "${baseUrl}/${pathStr}" : pathStr
}
%>
<% if (entry.type == 'directory') { %>
    <li class="folder">
        <span>${entry.name}</span>
        <ul>
            <% (entry.children ?: []).each { child -> %>
                ${partial('sidebarItem', [item: child])}
            <% } %>
        </ul>
    </li>
<% } else if (entry.type == 'file') { %>
    <li class="file"><a href="${toHtmlPath(entry.path)}">${entry.name}</a></li>
<% } %>
