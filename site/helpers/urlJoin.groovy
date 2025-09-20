package scaffold.basic.helpers

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

return { Object first, Options options ->
    def parts = []
    if (first != null) parts << first.toString()
    options?.params?.each { p -> if (p != null) parts << p.toString() }

    parts = parts.findAll { it != null && it.toString().trim().length() > 0 }
    if (parts.isEmpty()) return ""

    String firstRaw = parts[0]
    boolean hasScheme = (firstRaw ==~ /^[a-zA-Z][a-zA-Z0-9+.-]*:\/\/.*/)
    boolean startsWithDoubleSlash = firstRaw.startsWith("//")
    boolean leadingSlash = firstRaw.startsWith("/")

    def cleaned = []
    parts.eachWithIndex { String seg, int idx ->
        String s = seg
        if (idx == 0) {
            // Keep the first segment's leading part intact; trim trailing slashes only
            s = s.replaceAll(/\/+$/, "")
        } else {
            // For subsequent segments, trim both ends
            s = s.replaceAll(/^\/+/, "")
            s = s.replaceAll(/\/+$/, "")
        }
        if (s.length() > 0) cleaned << s
    }

    String joined = cleaned.join("/")

    if (!hasScheme && !startsWithDoubleSlash && leadingSlash && !joined.startsWith("/")) {
        joined = "/" + joined
    }

    if (startsWithDoubleSlash && !joined.startsWith("//")) {
        joined = "//" + joined.replaceFirst(/^\/+/, "")
    }

    return joined
} as Helper
