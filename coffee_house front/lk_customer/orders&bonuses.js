var user_id = localStorage.getItem("userNowId");

fetch('http://127.0.0.1:8080/orders/getOrders/' + user_id)
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#orders').innerHTML);
    const html = template({ orders: data });
    document.querySelector('#orders-list').innerHTML = html;
  });

  fetch('http://127.0.0.1:8080/users/getBonuses/' + user_id)
  .then(response => response.json()) 
  .then(data => {
    var valueElement = document.getElementById("value");

    valueElement.innerHTML = data;
  })
  .catch(error => console.error(error)); 