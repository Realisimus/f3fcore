function fill() {
    let request = new XMLHttpRequest();
    request.open('GET', 'pilots.json', true);
    request.onreadystatechange = function (e) {
        if (this.readyState === 4) {
            if (this.status === 200) {
                let pilots = JSON.parse(this.responseText);
                let tbody = document.getElementById('pilots').getElementsByTagName('TBODY')[0];
                for (let i = 0; i < pilots.length; i++) {
                    let row = document.createElement('tr');
                    let td1 = document.createElement('td');
                    let td2 = document.createElement('td');
                    let td3 = document.createElement('td');
                    let td4 = document.createElement('td');
                    let td5 = document.createElement('td');
                    td1.appendChild(document.createTextNode(pilots[i].id));
                    td2.appendChild(document.createTextNode(pilots[i].login));
                    td3.appendChild(document.createTextNode(pilots[i].first_name));
                    td4.appendChild(document.createTextNode(pilots[i].last_name));
                    td5.appendChild(document.createTextNode(pilots[i].license));
                    row.appendChild(td1);
                    row.appendChild(td2);
                    row.appendChild(td3);
                    row.appendChild(td4);
                    row.appendChild(td5);
                    tbody.appendChild(row);
                }
            }
            else {
                alert('Network issue');
            }
        }
    };
    request.send();
}