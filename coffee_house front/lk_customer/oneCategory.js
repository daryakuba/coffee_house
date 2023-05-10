fetch('http://127.0.0.1:8080/products')
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#products').innerHTML);
    const html = template({ products: data });
    document.querySelector('#products-list').innerHTML = html;
  });



  
