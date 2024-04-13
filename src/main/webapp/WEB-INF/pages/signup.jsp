<html lang="en">
<head>

<link rel="icon" type="image/png" href="/static/images/favicon1.ico"/>

    <script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
         integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
         crossorigin="anonymous">
    </script>




    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="styles.css" />
    <!-- CSS only -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap4.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />






    <title>Sign up for X</title>
<style>
    body {
        width: 100%;
        min-height: 100vh;
        box-sizing: border-box;
        font-family: "Open Sans";
    }

    .container {
        position: relative;
        min-height: 100vh;
        max-width: 100% !important;
        background-color: #161623;
        overflow: hidden;
        display: grid;
        place-items: center;
    }

    .container::before {
        content: "";
        position: absolute;
        width: 400px;
        height: 400px;
        border-radius: 50%;
        background: #7B66FF;
        /* transform: scale(0.5) translate(-100%, -100%); */
        /* transform: translateX(4em); */
        /* transform: scale(2, 0.5); */

        animation: move-up6 2s ease-in infinite alternate-reverse;

    }

    .container::after {
        content: "";
        position: absolute;
        vertical-align: bottom;
        width: 250px;
        height: 250px;
        border-radius: 50%;
        background: #5FBDFF;

        animation: move-up6 2s ease-in infinite alternate-reverse;
    }
    @keyframes move-up6 {
      to {
            transform: translateY(-50px);

        }
      }

    a {
        text-decoration: none;
    }

    .login {
        position: relative;
        width: 350px;
        padding: 30px;
        height: fit-content;
        background-color: rgba(255, 255, 255, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.5);
        border-radius: 15px;
        z-index: 10;
        backdrop-filter: blur(25px);
        box-shadow: 10px 10px 40px rgba(0, 0, 0, 0.2),
        -10px -10px 40px rgba(0, 0, 0, 0.2);
    }

    @media (max-width:400px) {
        .login {
            width: 90%;
        }
    }

    .login h1 {
        font-size: 1.8rem;
        color: #fff;
        margin-bottom: 40px;
        margin-top: 0;
        text-align: center;
    }
    .login p {
                    color: #fff;
        }

    .login form {
        width: 100%;
        height: 100%;
        outline: none;
        border: none;
    }

    .login form .input-box {
        width: 100%;
        position: relative;
        margin-bottom: 30px;
        display: flex;
    }

    .login form .input-box input {
        width: 100%;
        border: none;
        padding: 1rem 2.7rem 1rem 1rem;
        border-radius: 10px;
        color: #fff;
        background-color: rgba(255, 255, 255, 0.2);
        border: 1px solid rgba(255, 255, 255, 0.4);
    }

    .login form .input-box input::placeholder {
        color: #fff;
        font-size: 0.8rem;
        transition: 0.5s ease;
    }

    .login form .input-box input:focus::placeholder {
        opacity: 0;
    }

    .login form .input-box input:focus {
        outline: none;
    }

    .login form .input-box i {
        position: absolute;
        top: 50%;
        right: 15px;
        transform: translateY(-50%);
        color: #fff;
        font-size: 1.2rem;
    }

    .login form .rembar {
        margin-bottom: 30px;
        width: 100%;
    }

    .login form .rembar input {
        appearance: none;
    }

    .login form .rembar label {
        color: #fff;
        position: relative;
        width: 100%;
        padding-left: 35px;
        font-size: 0.9rem;
    }

    .login form .rembar label::before {
        content: "";
        position: absolute;
        left: 0;
        top: 50%;
        transform: translateY(-50%);
        width: 22px;
        height: 22px;
        border-radius: 50%;
        background-color: rgba(255, 255, 255, 0.2);
    }

    .login form .rembar label::after {
        content: "";
        position: absolute;
        left: 4px;
        top: 50%;
        transform: translateY(-50%);
        width: 14px;
        height: 14px;
        border-radius: 50%;
        background-color: #fff;
        transition: 0.5 ease;
        opacity: 0;
        box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
    }

    .login form .rembar input:checked + label::after {
        opacity: 1;
    }

    .login form p{
    color:red;  display:none;
    }

    .login form button {
        width: 100%;
        border: none;
        padding: 1rem 1rem 1rem 1rem;
        border-radius: 10px;
        color: #fff;
        margin-bottom: 30px;
        background-color: #161623;
        border: 1px solid rgba(255, 255, 255, 0.4);
        transition: 0.5s ease;
        cursor: pointer;
        font-weight: 600;
    }

    .login form button:hover {
              text-align: center;
              background-color: #111;
    }

    .login form .links {
               width: 100%;
               display: flex;
               justify-content: space-between;
               gap: 10px;
           }

        .login form .links a {
            color: #fff;
            font-weight: 100;
            font-size: 0.7rem;
        }



</style>


  </head>
  <body>
    <div class="container">

        <div class="login">

            <h1>Join CoderX Today</h1>

            <div class="rembar">
            <p class="rembar">Welcome to the coders Network</p>
            </div>

            <form action="">



                <div class="input-box">
                    <input id="signup-name" type="Name" placeholder="Username">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="input-box">
                    <input id="signup-email" type="email" placeholder="Email">
                    <i class="fa fa-envelope"></i>
                </div>

                <div class="input-box">
                    <input id="signup-password" type="password" placeholder="Password">
                    <i class="fa fa-lock"></i>
                </div>

                <div class="rembar">
                    <p for="rembar" id="signup-error">  </p>
                </div>

                <div class="rembar">
                <input id="rembar" type="checkbox">
                <label for="rembar">Remember me</label>
                </div>

                <button type="button" id="btn-signup">Sign Up</button>

                <div class="links">
                     <a href="/login/welcome">Already have an account?</a>
                </div>

            </form>
        </div><!-- End Login -->
    </div>

     <script>
        function validateSignUpForm(){
                        var name=$("#signup-name").val();
                        var email=$("#signup-email").val();
                        var password=$("#signup-password").val();

                        var error="";
                        //if name is empty
                        if(!name){
                            error+="Please Fill Username ";
                        }
                        if(!email){
                            error+="Please Fill Email ";
                        }
                        if(!password){
                            error+="Please Fill Password ";
                        }
                        if(password.length < 3){
                            error+="Password length must be greater than three Characters.\n";
                        }


                        $("#signup-error").html(error);//passage


                        if(error.length>0){
                        return false;
                        }

                        return true;
                }

         $("#btn-signup").click(function()
         {
          var value=validateSignUpForm();
          if(!value){
           $("#signup-error").show();
           //alert("Please Fill the Form");
           }
           else{
           $("#signup-error").hide();
           //alert("The Button was clicked");

           var name=$("#signup-name").val();
           var email=$("#signup-email").val();
           var password=$("#signup-password").val();

           var user={
               "name":name,
               "email":email,
               "password":password
              };

          $.ajax({
            type: "POST",
            url: "/signup",
            data: JSON.stringify(user),
            success: function(response){

                if(!!response){
                     if(response.user_created === true){
                        //alert(response.message);
                        location.href="/login/welcome";
                     }else{
                        //alert(response.message);
                        location.href="/login/welcome";
                     }
                }

            },
            contentType: 'application/json'
          });
          }
        });
     </script>


  </body>
</html>