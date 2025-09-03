import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import groovy.util.ConfigSlurper

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


                // Look for a Groovy front matter block and extract the title when present
                def title
                if (file.name.toLowerCase().endsWith(".md")) {
                    def lines = file.readLines()
                    if (lines && lines[0] ==~ /^---\s*$/) {

                        def fmLines = []

                        for (int i = 1; i < lines.size(); i++) {
                            def line = lines[i]
                            if (line ==~ /^---\s*$/) {
                                break
                            }
                            fmLines << line
                        }
                        if (fmLines) {
                            try {
                                def config = new ConfigSlurper().parse(fmLines.join("\n"))
                                title = config.title?.toString()
                            } catch (ignored) {
                                // ignore malformed front matter
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
