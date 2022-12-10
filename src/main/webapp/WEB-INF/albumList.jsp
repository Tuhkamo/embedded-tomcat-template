<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Music</title>
<body>
	<h1>All albums by artist</h1>
	<ol>
		<c:forEach items="${albums}" var="album">
			<li>${album.getName() }</li>
		</c:forEach>
	</ol>
</body>
</html>