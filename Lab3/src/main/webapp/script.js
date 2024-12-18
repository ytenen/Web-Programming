let canvas, ctx;
let R = 1.0;


window.onload = function() {
    initCanvas();
};

function initCanvas() {
    canvas = document.getElementById("areaCanvas");
    ctx = canvas.getContext("2d");

    // Инициализация графика
    drawArea(R);

    // Обновляем таблицу при загрузке страницы
    const tableDiv = document.getElementById("table");
    if (tableDiv) {
        jsf.ajax.request(tableDiv, null, {
            render: "results-table"
        });
    } else {
        console.error("Form containing the results table not found.");
    }
}


function drawArea(radius) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const scale = 200;

    ctx.fillStyle = "IndianRed";
    //прямоугольник
    ctx.fillRect(centerX, centerY, -scale/2, -scale);
    //окруж
    ctx.beginPath();
    ctx.arc(centerX, centerY, scale / 2, 0, 0.5 * Math.PI);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fill();
    //треугольник
    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX - scale, centerY);
    ctx.lineTo(centerX, centerY + scale );
    ctx.closePath();
    ctx.fill();

    ctx.strokeStyle = "black";
    ctx.beginPath();
    ctx.moveTo(0, centerY);
    ctx.lineTo(canvas.width, centerY);
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX, canvas.height);
    ctx.stroke();

    ctx.fillStyle = "black";
    ctx.font = "12px Arial";
    ctx.fillText(radius.toFixed(2), centerX + scale, centerY);
    ctx.fillText((radius / 2).toFixed(2), centerX + scale / 2, centerY);
    ctx.fillText((-radius).toFixed(2), centerX - scale, centerY);
    ctx.fillText((-radius / 2).toFixed(2), centerX - scale / 2, centerY);
    ctx.fillText(radius.toFixed(2), centerX, centerY - scale);
    ctx.fillText((radius / 2).toFixed(2), centerX, centerY - scale / 2);
    ctx.fillText((-radius / 2).toFixed(2), centerX, centerY + scale / 2);
    ctx.fillText((-radius).toFixed(2), centerX, centerY + scale);
    drawPoints();
}
function isPointInsideArea(x, y, r) {
    // Проверка четверти круга (вторая четверть: x ≥ 0 и y ≥ 0)
    if (x >= 0 && y <= 0 && (x * x + y * y) <= (r * r/4)) {
        return true;
    }
    // Проверка прямоугольника (третья четверть: x ≤ 0 и y ≥ 0)
    if (x <= 0 && y >= 0 && x >= -r/2 && y <= r) {
        return true;
    }
    // Проверка треугольника (четвертая четверть: x ≤ 0 и y ≤ 0)
    if (x <= 0 && y <= 0 && y >= -x - r) {
        return true;
    }
    return false;
}
function drawPoints() {
    const centerX = 250;
    const centerY = 250;

    fetch('api/getResults', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(points => {
            points.forEach(point => {
                 px = centerX + point.x * 200 / R;
                 py = centerY - point.y * 200 / R ;

                ctx.fillStyle = isPointInsideArea(point.x,point.y,R) ? "green" : "red";
                ctx.beginPath();
                ctx.arc(px, py, 4, 0, Math.PI * 2);
                ctx.fill();
            })
        })
        .catch(error => {
            console.error('Error in points getting:', error);
        });
}

function setX(value){
    console.log("setting X");
    document.getElementById('j_idt12:xInput').value = value;
}
function sendForm(){
    console.log("sending form")
    let X = document.getElementById('j_idt12:xInput').value;
    let Y = document.getElementById('j_idt12:yInput').value;
    sendCoordinatesToServer(X,Y,R, true);
}

function onCanvasClick(event) {
    const rect = canvas.getBoundingClientRect();

    // Координаты клика относительно canvas
    const clickX = event.clientX - rect.left;
    const clickY = event.clientY - rect.top;

    // Координаты центра canvas
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    // Пересчет в координаты относительно центра и масштабирование
    const x = (clickX - centerX)/200*R;
    const y = (centerY - clickY)/200*R;

    sendCoordinatesToServer(x, y, R, false);
}

function sendCoordinatesToServer(x, y, r, isForm) {
    fetch('api/addResult', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ x: x, y: y, r: r })
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                drawArea(r);
                updateResultsTable(x, y, r, result.isInside, isForm);
            } else {
                alert("Bad request 400");
                console.error('Failed to add result:', result.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function updateRadius(value) {
    R = parseFloat(value);
    document.getElementById("j_idt12:rInput").value = value;
    drawArea(R);
}

function updateResultsTable(x, y, r, isInside,isForm) {
    const resultsTable = document.querySelector("#results-table tbody");

    if (!resultsTable) {
        console.error("Table or tbody not found");
        return;
    }

    const newRow = document.createElement("tr");

    const xCell = document.createElement("td");
    if (isForm){
        xCell.textContent = x;
    }else{
        xCell.textContent = x.toFixed(2);
    }
    newRow.appendChild(xCell);

    const yCell = document.createElement("td");
    if (isForm){
        yCell.textContent = y;
    }else{
        yCell.textContent = y.toFixed(2);
    }
    newRow.appendChild(yCell);

    const rCell = document.createElement("td");
    rCell.textContent = r;
    newRow.appendChild(rCell);

    const resultCell = document.createElement("td");
    resultCell.textContent = isInside ? 'Hit' : 'Fail';
    newRow.appendChild(resultCell);

    resultsTable.appendChild(newRow);
}

function validateInputY(event, min, max) {
    const char = String.fromCharCode(event.which);

    // Разрешаем "-" только в начале строки
    if (char === "-" && event.target.value === "") {
        return; // Позволяем вводить знак "-"
    }

    // Формируем новое значение после добавления символа
    const newValue = event.target.value + char;

    // Проверяем, является ли новое значение числом
    const parsedValue = parseFloat(newValue);

    if (isNaN(parsedValue) || newValue === "") {
        event.preventDefault();
        alert("Please, enter a numeric value.");
        return;
    }

    // Проверяем диапазон
    if (parsedValue < min || parsedValue > max) {
        event.preventDefault();
        alert(`Please, enter the value between ${min} and ${max}`);
    }
}





