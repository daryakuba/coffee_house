fetch('http://127.0.0.1:8080/tables/rent')
  .then(response => response.json())
  .then(data => {
    const template = Handlebars.compile(document.querySelector('#tables').innerHTML);
    const html = template({ tables: data });
    document.querySelector('#tables-list').innerHTML = html;
  });

  async function deleteRent(button){
    var tableId = button.getAttribute("data-id");

    let response1 = await fetch('http://127.0.0.1:8080/tables/delUser/' + tableId);
      
    if (response1.status == 200){
        let response2 = await fetch('http://127.0.0.1:8080/tables/freeStatus/' + tableId);

        if(response2.status == 200){
            alert("Бронь снята");
        }
    }

  }