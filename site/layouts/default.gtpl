<% def assetPath = { p -> baseUrl ? "${baseUrl}/${p}" : p } %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
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
<main class="container prose">
${content}
</main>
</body>
</html>
