<%
def assetPath = { p -> baseUrl ? "${baseUrl}/${p}" : p }
def currentPage = binding.variables.containsKey('page') ? page : [title: title, meta: [:]]
def pageTitle = currentPage?.title ?: title
def pageMeta = currentPage?.meta ?: [:]
def toPageHref = { p ->
    if (!p) return '#'
    def href = p.toString()
    if (!href.startsWith('http://') && !href.startsWith('https://') && !href.startsWith('#')) {
        if (!href.toLowerCase().endsWith('.html')) {
            href = href.replaceFirst(/\.[^\/]+$/, '')
            href = "${href}.html"
        }
        href = baseUrl ? "${baseUrl}/${href}" : href
    }
    href
}
def defaultSidebar = [
    title: 'Arden',
    subtitle: 'Constructed, phonetic, clear.',
    sections: [
        [
            title: 'Start Here',
            links: [
                [label: 'Home Guide', href: 'index.html'],
                [label: 'Canonical Grammar', href: 'canonical-grammar.html'],
                [label: 'Phoneme & Orthography', href: 'phoneme-orthography.html'],
                [label: 'Conversational Core', href: 'conversational-core.html'],
                [label: 'Common Daily Sentences', href: 'common-daily-sentences.html']
            ]
        ],
        [
            title: 'Core Grammar',
            links: [
                [label: 'Auxiliary & Discourse', href: 'auxiliary-discourse.html'],
                [label: 'Transportation & Verbs', href: 'transportation-verbs.html'],
                [label: 'Adjectives & Adverbs', href: 'adjectives-adverbs.html'],
                [label: 'Quick Summary', href: 'arden-summary.html']
            ]
        ],
        [
            title: 'Vocabulary',
            links: [
                [label: 'Body, Clothing, Color', href: 'body-clothing-color.html'],
                [label: 'Food, Home, Jobs, Location', href: 'food-home-jobs-location.html'],
                [label: 'Animals, Art, Beverages', href: 'animals-art-beverages.html'],
                [label: 'Months, Nature, Numbers, People', href: 'months-nature-numbers-people.html'],
                [label: 'Days, Directions, Electronics', href: 'days-directions-electronics.html'],
                [label: 'Materials, Measurements, Modal', href: 'materials-measurements-misc-modal.html'],
                [label: 'Pronouns, Seasons, Society, Time', href: 'pronouns-seasons-society-time.html']
            ]
        ]
    ]
]
def pageSidebar = pageMeta.sidebar ?: defaultSidebar
def normalizeLinks = { links ->
    (links ?: []).collect { link ->
        if (link instanceof Map) {
            return [label: link.label ?: link.href, href: link.href]
        }
        def raw = link?.toString() ?: ''
        def parts = raw.split(/\|/, 2)
        [label: parts[0], href: parts.size() > 1 ? parts[1] : parts[0]]
    }
}
def resolveSections = { data ->
    if (data?.sections) {
        return data.sections.collect { section ->
            [
                title: section.title,
                links: normalizeLinks(section.links)
            ]
        }
    }
    def derivedSections = []
    if (data?.startHere) {
        derivedSections << [title: 'Start Here', links: normalizeLinks(data.startHere)]
    }
    if (data?.coreGrammar) {
        derivedSections << [title: 'Core Grammar', links: normalizeLinks(data.coreGrammar)]
    }
    if (data?.vocabulary) {
        derivedSections << [title: 'Vocabulary', links: normalizeLinks(data.vocabulary)]
    }
    derivedSections
}
def sidebarSections = resolveSections(pageSidebar)
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${pageTitle}</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${assetPath('css/normalize.css')}">
    <link rel="stylesheet" href="${assetPath('css/skeleton.css')}">
    <link rel="stylesheet" href="${assetPath('css/style.css')}">

    <link rel="icon" type="image/png" href="${assetPath('images/favicon.png')}">
</head>
<body>
<main class="container layout">
    <nav class="sidebar">
        <div class="sidebar-brand">
            <img src="${assetPath('images/arden.png')}" alt="Arden logo" class="sidebar-logo">
            <div>
                <p class="sidebar-title">${pageSidebar.title ?: 'Arden'}</p>
                <% if (pageSidebar.subtitle) { %>
                <p class="sidebar-subtitle">${pageSidebar.subtitle}</p>
                <% } %>
            </div>
        </div>
        <% if (sidebarSections) { %>
        <div class="sidebar-sections">
            <% sidebarSections.each { section -> %>
            <section class="sidebar-section">
                <% if (section.title) { %>
                <p class="sidebar-section-title">${section.title}</p>
                <% } %>
                <ul class="nav-list">
                    <% (section.links ?: []).each { link -> %>
                    <li class="file"><a href="${toPageHref(link.href)}">${link.label ?: link.href}</a></li>
                    <% } %>
                </ul>
            </section>
            <% } %>
        </div>
        <% } %>
    </nav>
    <div class="prose">
        ${content}
    </div>
</main>
</body>
</html>
