


let canvas, ctx;
let R = 1.0;


function initCanvas() {
    canvas = document.getElementById("areaCanvas");
    ctx = canvas.getContext("2d");
    drawArea(R);
}

let points = [];


function drawArea(radius) {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const scale = 200;

    ctx.fillStyle = "IndianRed";

    ctx.fillRect(centerX, centerY, scale, scale/2);

    ctx.beginPath();
    ctx.arc(centerX, centerY, scale / 2, Math.PI, 1.5 * Math.PI);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fill();

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

    points.forEach(point => {
        const relativeX = (point.absoluteX / R) * radius;
        const relativeY = (point.absoluteY / R) * radius;

        point.isInside = isPointInsideArea(relativeX, relativeY, radius);

        drawPoint(relativeX, relativeY, point.isInside);
    });
}
function isPointInsideArea(x, y, radius) {
    if (x <= radius && x >= 0 && y >= -radius/2 && y <= 0) {
        return true;
    }
    if (x >= -radius / 2 && x <= 0 && y >= 0 && y <= radius / 2) {
        if ((x * x + y * y) <= (radius / 2) * (radius / 2)) {
            return true;
        }
    }
    return x <= 0 && y <= 0 && y >=(-x-radius);
}
function drawPoint(x, y, isInside) {
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const pointX = centerX + (x / R) * 200;
    const pointY = centerY - (y / R) * 200;

    ctx.fillStyle = isInside ? "green" : "red";
    ctx.beginPath();
    ctx.arc(pointX, pointY, 4, 0, Math.PI * 2);
    ctx.fill();
}

function setX(value){
    document.getElementById('j_idt11:xInput').value = value;
}
function sendForm(){
    console.log("sending form")
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    let X = parseFloat(document.getElementById('j_idt11:xInput').value);
    let Y = parseFloat(document.getElementById('j_idt11:yInput').value);
    const pointX = centerX + (X / R) * 200;
    sendCoordinatesToServer(X,Y,R);
}

function onCanvasClick(event) {
    const rect = canvas.getBoundingClientRect();
    const x = ((event.clientX - rect.left) - canvas.width / 2) / 200;
    const y = ((canvas.height / 2) - (event.clientY - rect.top)) / 200;

    const relativeX = x * R;
    const relativeY = y * R;

    sendCoordinatesToServer(relativeX, relativeY, R);
}

function sendCoordinatesToServer(x, y, r) {
    fetch('api/addResult', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ x: x.toFixed(2), y: y.toFixed(2), r: r.toFixed(2) })
    })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                points.push({
                    absoluteX: x,
                    absoluteY: y,
                    isInside: result.isInside
                });
                drawArea(r);
                updateResultsTable(x, y, r, result.isInside);
            } else {
                console.error('Failed to add result:', result.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function updateRadius(value) {
    R = parseFloat(value);
    console.log(R);
    console.log(value);
    document.getElementById("rInput").value = value;
    drawArea(R);
};

function updateResultsTable(x, y, r, isInside) {
    const resultsTable = document.querySelector("#results-table tbody");

    if (!resultsTable) {
        console.error("Table or tbody not found");
        return;
    }

    const newRow = document.createElement("tr");

    const xCell = document.createElement("td");
    xCell.textContent = x.toFixed(2);
    newRow.appendChild(xCell);

    const yCell = document.createElement("td");
    yCell.textContent = y.toFixed(2);
    newRow.appendChild(yCell);

    const rCell = document.createElement("td");
    rCell.textContent = r;
    newRow.appendChild(rCell);

    const resultCell = document.createElement("td");
    resultCell.textContent = isInside ? 'Попал' : 'Промах';
    newRow.appendChild(resultCell);

    resultsTable.appendChild(newRow);
}

function validateInputY(event, min, max) {
    const char = String.fromCharCode(event.which);
    const newValue = event.target.value + char;
    const parsedValue = parseFloat(newValue);

    // Проверка на число (и на пустое поле)
    if (isNaN(parsedValue) || newValue === "") {
        event.preventDefault();
        event.target.value = "";
        alert("Пожалуйста, введите числовое значение.");
        return;
    }

    // Проверка на диапазон
    if (parsedValue < min || parsedValue > max) {
        event.preventDefault();
        event.target.value = "";
        alert(`Пожалуйста, введите значение между ${min} и ${max}`);
    }
}

window.onload = function() {
    initCanvas();
};


