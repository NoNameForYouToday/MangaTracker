
window.onload = function () {
    updateTable();

    function reCheckRequest(){
        fetch("/api/recheck", {
            method:"POST"
        });
    }

    const recheckBtn = document.getElementById("recheckBtn");

    reCheckRequest.addEventListener("click",reCheckRequest)
    function updateTable() {
        const table = document.querySelector("#manga-table");
        const tablebody = document.querySelector("#manga-table tbody");
        tablebody.innerHTML = "";

        fetch("/api/mangas", {
            method: "GET",

        }).then(res => res.json())
            .then(data => {
                console.log(data);
         data.forEach(entry => {
                            var row = table.insertRow(-1);

                            var mangaID_cell = row.insertCell(0);
                            var title_cell = row.insertCell(1);
                            var language_cell = row.insertCell(2);
                            var hVolume_cell = row.insertCell(3);
                            var hChapter_cell = row.insertCell(4);
                            var state_cell = row.insertCell(5);
                            var removeButton_cell = row.insertCell(-1);
                            mangaID_cell.textContent = entry["mangaID"];
                            title_cell.textContent = entry["title"];
                            language_cell.textContent = entry["language"];
                            hVolume_cell.textContent = entry["highestVolume"];
                            hChapter_cell.textContent = entry["highestChapter"];


                            //creates the delete Button
                            const button = document.createElement('button');
                            button.innerText = "Delete";
                            button.classList.add("Delete-btn");
                            button.addEventListener("click", (event => {
                                location.href = "/api/delete?mangaID=" + entry["mangaID"];
                            }));
                            removeButton_cell.appendChild(button);
                        })
            });
    }
}