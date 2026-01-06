<nav class="sidebar">
    <p><strong>Arden</strong> Language Topics</p>
    <ul class="nav-list prose">
        <% (navigation ?: []).each { item -> %>
            ${partial('sidebarItem', [item: item])}
        <% } %>
    </ul>
</nav>
