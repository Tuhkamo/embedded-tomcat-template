<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Music</title>
<body>
	<h1>All artists</h1>
	<form method="post">
			<input name="name" type="text" required
				placeholder="type artist here..." autofocus /> <input type="submit"
				value="Add to list" />
		</form>
	<ol>
		<c:forEach items="${artists}" var="artist">
			<li><a href="/albums?ArtistId=50">${artist.getName() }</a></li>
		</c:forEach>
	</ol>
</body>
</html>