<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Croissant Loader</title>
  <style>
    body {
      background-color: #fbeaff;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: 'Comic Sans MS', cursive;
    }

    .loader-container {
      text-align: center;
      animation: fadeOut 0.3s ease-in-out 1.2s forwards;
    }

    .croissant {
      width: 200px;
      height: 100px;
      background-color: #f8d7ff;
      border: 4px solid #b567a3;
      border-radius: 100px 100px 60px 60px;
      position: relative;
      overflow: hidden;
      box-shadow: 0 4px 8px rgba(178, 112, 180, 0.2);
      margin-bottom: 10px;
    }

    .croissant-fill {
      background: linear-gradient(to right, #e188f5, #ffb3fe);
      height: 100%;
      width: 0%;
      animation: fillCroissant 1s linear forwards;
    }

    @keyframes fillCroissant {
      0% { width: 0%; }
      100% { width: 100%; }
    }

    .percentage {
      font-size: 1.2em;
      color: #a64db3;
      margin-bottom: 8px;
    }

    .loading-text {
      color: #a64db3;
      font-size: 1.3em;
    }

    @keyframes fadeOut {
      to {
        opacity: 0;
        visibility: hidden;
      }
    }

    #message {
      display: none;
      color: #a64db3;
      font-size: 1.2em;
      text-align: center;
    }

    #message a {
      display: block;
      margin-top: 8px;
      color: #a64db3;
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="loader-container" id="loader">
    <div class="croissant">
      <div class="croissant-fill" id="fillBar"></div>
    </div>
    <div class="percentage" id="percentText">0%</div>
    <p class="loading-text">Flaking in progress...</p>
  </div>

  <div id="message">
    Sorry for the inconvenience. If the problem remains, contact us.
    <a href="${pageContext.request.contextPath}/home">Back to Home</a>
  </div>

  <script>
    const percentText = document.getElementById("percentText");
    let percent = 0;

    const interval = setInterval(() => {
      percent++;
      percentText.textContent = percent + "%";
      if (percent >= 100) {
        clearInterval(interval);
      }
    }, 10);

    window.addEventListener("load", () => {
      setTimeout(() => {
        document.getElementById("loader").style.display = "none";
        document.getElementById("message").style.display = "block";
      }, 1200);
    });
  </script>
</body>
</html>
