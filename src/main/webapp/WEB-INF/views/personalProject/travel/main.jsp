<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Info</title>
    <style>
        
        html {
            height: 100%;
            margin: 0;
            padding: 0;
        }


        * {
            box-sizing: border-box; /* 모든 요소에 대해 border와 padding을 크기 안에 포함 */
        }

        body {
            font-family: 'Inter', sans-serif;
            height: 100%;
            margin: 0;
            padding: 0;
            background: white;
        }


        /* 모바일 줄바꿈용 */
        .mobile-hide {
             display: inline;   
        }

        /* General Styles */
        h1, h2, p {
            margin: 0;
            padding: 0;
        }

        .header .wrapper {
            background-color: #346154;
            height: 100vh;
            color: white;
            text-align: center;
            padding: 60px 20px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: relative; /* Important to position the ticker inside */
        }

        .header .wrapper h1 {
            font-size: 50px;
            font-weight: 700;
            letter-spacing: -2px;
        }

        .header .wrapper h2 {
            font-size: 24px;
            font-weight: 500;
            margin-bottom: 30px;
            letter-spacing: -0.5px;
            color: rgba(255, 255, 255, 0.6);
            line-height: 1.5em;
        }

        .cta-buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .cta-button {
            width: 160px;
            background-color: white;
            color: #333;
            font-weight: 700;
            font-size: 14px;
            padding: 10px 20px;
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            border: none;
        }

        .cta-button:hover {
            background-color: #f0f0f0;
        }

        .cta-button:active {
            background-color: #d0d0d0;
        }

        /* Ticker Styles */
        .ticker-wrapper {
            position: absolute;
            width: 100%;
            overflow: hidden;
            color: white;
            padding: 5px 0;
            font-size: 18px;
            font-weight: bold;
        }

        .ticker-wrapper1 {
            top: 50px;  /* Adjust the distance from the top of the wrapper */
        }

        .ticker-wrapper2 {
            bottom: 90px;  /* Adjust the distance from the top of the wrapper */
        }

        .ticker {
            display: flex;
            animation: scroll 20s linear infinite;
            white-space: pre; /* 공백 보존 */
        }

        .ticker-item {
            padding: 0 30px;
            white-space: nowrap;
        }

        @keyframes scroll {
            0% {
                transform: translateX(100%);
            }
            100% {
                transform: translateX(-100%);
            }
        }

        /* Responsive Design */
        @media (max-width: 1199px) {
            .header .wrapper {
                padding: 40px 20px;
            }

            .cta-button {
                font-size: 12px;
                padding: 8px 16px;
            }

            /* Adjust ticker font size and padding */
            .ticker-wrapper {
                font-size: 16px;  /* Smaller font size for smaller screens */
                padding: 4px 0;
            }

            .ticker-item {
                padding: 0 20px;  /* Reduced padding */
            }
        }


        @media (max-width: 809px) {
            .header .wrapper h1 {
                font-size: 36px;
            }

            .mobile-hide {
             display: none;   
            }

            .header .wrapper h2 {
                font-size: 20px;
                margin-bottom: 10px;
            }

            .cta-buttons {
                flex-direction: column;
            }

            .cta-button {
                width: 130px;
                font-size: 12px;
                padding: 8px 16px;
            }

            /* Further reduce ticker font size and padding for very small screens */
            .ticker-wrapper {
                font-size: 14px;  /* Even smaller font size */
                padding: 3px 0;
            }

            .ticker-item {
                padding: 0 10px;  /* Further reduced padding */
            }
        }
    </style>
</head>
<body>

    <div class="header">
        <div class="wrapper">
            <!-- Ticker(화면 오른쪽에서 왼쪽으로 흐르는 애니메이션션) -->
            <div class="ticker-wrapper ticker-wrapper1">
                <div class="ticker">
                    <div class="ticker-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;What time is our flight?<br>What airline is it?</div>
                </div>
            </div>

            <h1>All Our Travel Info<span class="mobile-hide"> in One Place</span></h1>
            <h2>No more keyword hunting<span class="mobile-hide"> or chatroom scrolling </span>!</h2>
            <div class="cta-buttons">
                <div class="cta-button" data-category="flight">Flight Info ✈️</div>
                <div class="cta-button" data-category="activities">Things to Do 🏖️</div>
            </div>

            <!-- Ticker(화면 오른쪽에서 왼쪽으로 흐르는 애니메이션션) -->
            <div class="ticker-wrapper ticker-wrapper2">
                <div class="ticker">
                    <div class="ticker-item">What’s the weather like there?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Where are we going now?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;What airport terminal is it? </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        
        // ============== 이벤트 리스너 =========
        // ## 버튼 클릭 시 상세보기로 이동
        const $clickButtons = document.querySelectorAll('.cta-button');
        console.log($clickButtons);
        $clickButtons.forEach(button => {
            button.addEventListener('click', e => {
                window.location.href = `/travel/\${e.target.dataset.category}`;
            })
        });

    </script>

    <!-- Optional Framer-style SVG templates -->
    <div id="svg-templates" style="position: absolute; overflow: hidden; bottom: 0; left: 0; width: 0; height: 0; z-index: 0; contain: strict" aria-hidden="true"></div>

</body>
</html>
