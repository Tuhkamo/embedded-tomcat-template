<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Shopping List</title>
</head>

<body>
	<h1>Shopping List</h1>
	<form method="post">
			<input name="title" type="text" required
				placeholder="type item here..." autofocus /> <input type="submit"
				value="Add to list" />
		</form>
	<table>
		<thead>
			<tr>
				<th>Product</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${items }" var="shoppingListItem">
				<tr>
					<td><c:out value="${shoppingListItem.getTitle() }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>