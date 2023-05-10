fetch('http://127.0.0.1:8080/products')
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#products').innerHTML);
    const html = template({ products: data });
    document.querySelector('#products-list').innerHTML = html;
  });

  async function createOrder() {
    var username = document.getElementById("username").value;

    let response = await fetch('http://localhost:8080/users/getIdByLogin/' + username);
    let text = await response.text();
    const user_id = parseInt(text);

    localStorage.setItem("userId", user_id);

    const response2 = await fetch('http://localhost:8080/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            user: {
              id: user_id
          },
          products: []
        })
    })

    if (response2.status == 200){
      alert("Заказ создан");
    }
  }

  async function addProduct(button){

    var productId = button.getAttribute("data-id");
    var user_id = localStorage.getItem("userId");
    
    let response = await fetch('http://127.0.0.1:8080/orders/addProductToOrder/' + user_id + "/" + productId);
      
    if (response.status == 200){
      alert("Товар добавлен");
    }

  }

  async function setBonuses(){
    var user_id = localStorage.getItem("userId");
    
    let response1 = await fetch('http://127.0.0.1:8080/orders/setBonuses/' + user_id);

    let response2 = await fetch('http://127.0.0.1:8080/orders/bonuses/' + user_id);

    if (response1.status == 200 && response2.status == 200){
      alert("Бонусы зачислены");
    }
  }