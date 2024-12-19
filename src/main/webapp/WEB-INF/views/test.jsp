<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, World!</h1>
    <script>
      async function test() {
        const a = await fetch("/test123", {
          method: 'POST',
          headers: { 'Content-Type' : 'application/json' },
          body: JSON.stringify({
            letter: " ",
            number: -100
          })
        });
        const b = await a.json();
        console.log(b);
      }
      // fetch("/test").then(response => response.json()).then(data => console.log(data));
      test();
    </script>
</body>
</html>