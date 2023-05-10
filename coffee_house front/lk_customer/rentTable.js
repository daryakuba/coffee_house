async function rent() {
    var user_id = localStorage.getItem("userNowId");

    const response0 = await fetch('http://localhost:8080/tables/usersTable/' + user_id);
    const text0 = await response0.text();
    const countTable = parseInt(text0);
    if (countTable == 0){

    const response = await fetch('http://localhost:8080/tables/exist');
    const text = await response.text();

    if (text == 'OK') {
        const response1 = await fetch('http://localhost:8080/tables/getFree');
        const text1 = await response1.text();
        const table_id = parseInt(text1);

        const response2 = await fetch('http://localhost:8080/tables/addUser/' + user_id + '/' + table_id);
       
        const response3 = await fetch('http://localhost:8080/tables/rentStatus/' + table_id);
        const text3 = await response3.text();
        
        alert("Столик забронирован");            
        
        
    }
    else{
        alert("Извините, на ближайшее время нет свободных столиков");
    }
}
else {
    alert("У вас уже забронирован столик!");
}
}