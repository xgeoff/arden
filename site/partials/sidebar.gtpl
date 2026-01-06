<%
def assetPath = { p -> baseUrl ? "${baseUrl}/${p}" : p }
%>
<nav class="sidebar">
    <div class="sidebar-brand">
        <img src="${assetPath('images/arden.png')}" alt="Arden logo" class="sidebar-logo">
        <div>
            <p class="sidebar-title">Arden</p>
            <p class="sidebar-subtitle">Constructed, phonetic, clear.</p>
        </div>
    </div>
    <ul class="nav-list prose">
        <% (navigation ?: []).each { item -> %>
            ${partial('sidebarItem', [item: item])}
        <% } %>
    </ul>
</nav>
