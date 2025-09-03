import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

return { Object context, Options options ->
    def pagesDir = options.context.get('pagesDir') as File
    if (!pagesDir?.exists() || !pagesDir.directory) {
        return []
    }

    def toSidebarItems
    toSidebarItems = { File dir, File root ->
        def items = []

        dir.eachFile { file ->
            if (file.directory) {
                def children = toSidebarItems(file, root)
                if (children) {
                    items << [
                            type    : 'directory',
                            name    : file.name,
                            children: children
                    ]
                }
            } else if (file.name ==~ /(?i).*\.(md|html?)$/) {
                def relativePath = root.toPath().relativize(file.toPath()).toString().replace(File.separator, "/")
                def name = file.name.replaceFirst(/(?i)\.(md|html?)$/, "")

                // Look for a YAML front matter block and extract the title when present
                def title
                if (file.name.toLowerCase().endsWith(".md")) {
                    def lines = file.readLines()
                    if (lines && lines[0] ==~ /^---\s*$/) {
                        def yamlLines = []
                        for (int i = 1; i < lines.size(); i++) {
                            def line = lines[i]
                            if (line ==~ /^---\s*$/) {
                                break
                            }
                            yamlLines << line
                        }
                        yamlLines.each { l ->
                            def m = l =~ /^\s*title\s*:\s*(.+)\s*$/
                            if (m) {
                                title = m[0][1].trim().replaceAll(/^['"]|['"]\$/, '')
                            }
                        }
                    }
                }

                def item = [
                        type: 'file',
                        name: name,
                        path: relativePath
                ]
                if (title) {
                    item.title = title
                }
                items << item
            }
        }

        items.sort { a, b ->
            a.type <=> b.type ?: a.name.toLowerCase() <=> b.name.toLowerCase()
        }
    }

    toSidebarItems(pagesDir, pagesDir)
} as Helper
