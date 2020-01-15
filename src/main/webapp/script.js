
function wykonaj(e)
{

    var tooltip = document.getElementById("tooltip");
    tooltip.style.display = "block";

    tooltip.style.left = e.clientX + 5 + "px";
    tooltip.style.top = e.clientY + 5 + "px";

}

function wykonaj2(e)
{

    var tooltip = document.getElementById("tooltip");
    tooltip.style.display = "none";
}


window.onload = function()
{
    var search = document.getElementById("search");
    search.onmousemove = function(event)
    {
        wykonaj(event);
    };

    search.onmouseout = function (event) {
        wykonaj2(event)
    }
};
