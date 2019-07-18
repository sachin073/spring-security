<jsp:include page="../header.jsp"></jsp:include>


<!-- Card -->
<div class="card">

    <!-- Card body -->
    <div class="card-body">

        <!-- Material form register -->
        <form action="/user/registration" method="post">
            <p class="h4 text-center py-4">Sign up</p>

            <!-- Material input text -->
            <div class="md-form">
                <i class="fa fa-user prefix grey-text"></i>
                <input type="text" id="materialFormCarfdNameEx" name="firstName"  class="form-control">
                <label for="materialFormCardNameEx" class="font-weight-light">Your name</label>
            </div>

            <div class="md-form">
                <i class="fa fa-user prefix grey-text"></i>
                <input type="text" id="materialFormCardNameEx"name="lastName" class="form-control">
                <label for="materialFormCardNameEx" class="font-weight-light">Your name</label>
            </div>

            <!-- Material input email -->
            <div class="md-form">
                <i class="fa fa-user prefix grey-text"></i>
                <input type="text" id="materialFormCardLNameEx" name="email" class="form-control">
                <label for="materialFormCardLNameEx" class="font-weight-light">Your email</label>
            </div>

            <!-- Material input password -->
            <div class="md-form">
                <i class="fa fa-lock prefix grey-text"></i>
                <input type="password" id="materialFormCardPasswordEx" name="password" class="form-control">
                <label for="materialFormCardPasswordEx" class="font-weight-light">Your password</label>
            </div>

            <div class="text-center py-4 mt-3">
                <button class="btn btn-cyan" type="submit">Register</button>
            </div>
        </form>
        <!-- Material form register -->

    </div>
    <!-- Card body -->

</div>
<!-- Card -->


<jsp:include page="../footer.jsp"></jsp:include>