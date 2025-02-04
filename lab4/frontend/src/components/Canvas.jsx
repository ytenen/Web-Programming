import React, { useRef, useEffect } from "react";

const CanvasArea = ({ r, results, onCanvasClick }) => {
    const canvasRef = useRef(null);

    useEffect(() => {
        drawCanvas();
    }, [r, results]);

    const drawCanvas = () => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");
        const canvasSize = 350;
        const center = canvasSize / 2;
        const scale = 100;

        ctx.clearRect(0, 0, canvasSize, canvasSize);

        const sign = Math.sign(r);
        const absR = Math.abs(r);


        ctx.fillStyle = "pink";

        ctx.beginPath();
        if (sign === 1) {
            ctx.arc(center, center, scale, Math.PI / 2, Math.PI);
        } else {
            ctx.arc(center, center, scale, -Math.PI / 2, 0);
        }
        ctx.lineTo(center, center);
        ctx.closePath();
        ctx.fill();

        if (sign === 1) {
            ctx.fillRect(center, center - scale, scale, scale);
        } else {
            ctx.fillRect(center, center, -scale, scale);
        }

        ctx.beginPath();
        ctx.moveTo(center, center);
        if (sign === 1) {
            ctx.lineTo(center + scale / 2, center);
            ctx.lineTo(center, center + scale);
        } else {
            ctx.lineTo(center - scale / 2, center);
            ctx.lineTo(center, center - scale);
        }
        ctx.closePath();
        ctx.fill();


        ctx.strokeStyle = "black";
        ctx.lineWidth = 1;
        ctx.beginPath();
        ctx.moveTo(center, 0);
        ctx.lineTo(center, canvasSize);
        ctx.moveTo(0, center);
        ctx.lineTo(canvasSize, center);
        ctx.stroke();


        ctx.fillStyle = "black";
        ctx.font = "12px Arial";
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";

        if (r<0){
            ctx.fillText("0", center, center + 12);
            ctx.fillText(`${-r}`, center + scale, center + 12);
            ctx.fillText(`${-r / 2}`, center + scale / 2, center + 12);
            ctx.fillText(`-${-r / 2}`, center - scale / 2, center + 12);
            ctx.fillText(`-${-r}`, center - scale, center + 12);
            ctx.fillText(`${-r}`, center - 12, center - scale);
            ctx.fillText(`${-r / 2}`, center - 12, center - scale / 2);
            ctx.fillText(`-${-r / 2}`, center - 12, center + scale / 2);
            ctx.fillText(`-${-r}`, center - 12, center + scale);
        }else{
            ctx.fillText("0", center, center + 12);
            ctx.fillText(`${r}`, center + scale, center + 12);
            ctx.fillText(`${r / 2}`, center + scale / 2, center + 12);
            ctx.fillText(`-${r / 2}`, center - scale / 2, center + 12);
            ctx.fillText(`-${r}`, center - scale, center + 12);
            ctx.fillText(`${r}`, center - 12, center - scale);
            ctx.fillText(`${r / 2}`, center - 12, center - scale / 2);
            ctx.fillText(`-${r / 2}`, center - 12, center + scale / 2);
            ctx.fillText(`-${r}`, center - 12, center + scale);
        }

        results.forEach((result) => {
            const adjustedX = result.x;
            const adjustedY = result.y;
            const pointX = center + (adjustedX / absR) * scale;
            const pointY = center - (adjustedY / absR) * scale;
            if (checkHit(adjustedX,adjustedY,r)){
                ctx.fillStyle = "green";
            }else{
                ctx.fillStyle = "red";
            }
            ctx.beginPath();
            ctx.arc(pointX, pointY, 4, 0, 2 * Math.PI);
            ctx.fill();
        });

    };

    return (
        <canvas
            ref={canvasRef}
            width="350"
            height="350"
            className="canvas"
            onClick={(e) => {
                const canvas = canvasRef.current;
                const rect = canvas.getBoundingClientRect();
                const xClick = (((e.clientX - rect.left - canvas.width / 2) / 100) * Math.abs(r)).toFixed(2);
                const yClick = (((canvas.height / 2 - (e.clientY - rect.top)) / 100) * Math.abs(r)).toFixed(2);
                onCanvasClick({ x: xClick, y: yClick });
            }}
        />
    );
};


function checkHit(x,y,r){
    if (r>=0){
        return ((x>=0 && y>=0 && y<=r && x <=r)|| (x<=0 && y<=0 && (x*x + y*y <= r*r)) || (x>=0 && y<=0 && y>=2*x-r));
    }else{
        return ((x<=0 && y<=0 && y>=r && x >=r)|| (x>=0 && y>=0 && (x*x + y*y <= r*r)) || (x<=0 && y>=0 && y<=2*x-r));
    }
}

export default CanvasArea;
