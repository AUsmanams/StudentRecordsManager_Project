// =========================================================
// FOOTER YEAR
// =========================================================

const yearElement = document.getElementById("year");

if (yearElement) {
    yearElement.textContent = new Date().getFullYear();
}


// =========================================================
// PROJECT BUTTONS
// =========================================================

const githubButton =
    document.getElementById("githubButton");

const downloadButton =
    document.getElementById("downloadButton");


// These links will be added later.
githubButton.addEventListener("click", function (event) {

    event.preventDefault();

    alert(
        "The GitHub repository link will be added after the repository is created."
    );
});


downloadButton.addEventListener("click", function (event) {

    event.preventDefault();

    alert(
        "The project download link will be added after the Java project is packaged."
    );
});