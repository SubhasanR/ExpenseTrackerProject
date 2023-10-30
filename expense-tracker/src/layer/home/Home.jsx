import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import MainExpenseLogo from "../../assets/MainExpenseLogo";
import "./Home.css";
import { useNavigate } from "react-router-dom";
import images_1 from "./s-1.png";
import images_2 from "./s-2.png";
import images_3 from "./s-3.png";
import images_4 from "./s-4.png";
import images_5 from "./k-1.png";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Navbars from "../../components/Navbars";

import bcrypt from "bcryptjs";

const Home = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const user = {
        email: formData.email,
        password: formData.password,
      };

      const requestBody = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
      };

      const response = await fetch("http://localhost:8105/login", requestBody);
      const responsedata = await response.json();

      if (responsedata.statusCodeValue === 401) {
        toast.error("Invalid e-mail or password!!", {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          theme: "colored",
        });
      } else if (responsedata.statusCodeValue === 200) {
        const existingUser = responsedata.body;
        if (
          existingUser &&
          (await bcrypt.compare(formData.password, existingUser.password))
        ) {
          localStorage.setItem("token", existingUser.token);
          navigate("expensetracker", { state: { existingUser } });
        }
      }
    } catch (error) {
      console.error(error);
      toast.error("An error occurred while processing your request", {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        theme: "colored",
      });
    }
  };

  const navigate = useNavigate();
  const handle = () => {
    toast("Login successful!", {
      position: "top-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
    });
  };

  return (
    <div class="pagealign" id="pa">
      <Navbars />
      {/* <div class="topnav">
        <a href="#pa">Home</a>
        <a href="#h-1">Features</a>
        <a href="#h-2">About</a>
      </div> */}

      <div className="home">
        <div id="login-page">
          <div class="login">
            <h2 class="login-title-head">Expense Tracker</h2>
            <h2 class="login-title">Login</h2>
            <form class="form-login">
              <div class="input-email">
                <i class="fas fa-envelope icon"></i>
                <input
                  type="email"
                  name="email"
                  id="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  placeholder="Enter your e-mail"
                  required
                />
              </div>
              <div class="input-password">
                <i class="fas fa-lock icon"></i>
                <input
                  type="password"
                  name="password"
                  id="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  placeholder="Enter your password"
                  required
                />
              </div>
              {/* <NavLink to="expensetracker"> */}{" "}
              <button type="submit" class="b1" onClick={handleSubmit}>
                Log in
              </button>
              {/* </NavLink> */}
              <NavLink to="signup">
                <button type="submit">Sign Up</button>
              </NavLink>
            </form>
            {/* <a href="#">Forgot your password?</a> */}
            <div class="created"></div>
          </div>

          <div className="header"></div>
          <div className="main">
            <div className="home-content">
              <h1 className="home-content-title">
                Track Your Expenses with Our App{" "}
              </h1>
              <h1 className="home-content-title">
                Simplify Your <span className="hightlight">Finances</span>
              </h1>
              <p className="home-content-subtitle">
                An expense tracker app is a powerful tool that can help you take
                control of your finances. With just a few taps, you can track
                all of your expenses, including your bills, groceries,
                entertainment, and more.{" "}
              </p>
              <p className="home-content-subtitle">
                This app can also help you set up a budget, monitor your
                spending, and alert you when you're close to reaching your
                limits.{" "}
              </p>
            </div>
            <div className="home-img">
              <MainExpenseLogo />
            </div>
          </div>
        </div>
      </div>
      <div className="features-cont" id="h-1">
        <div className="features-container">
          <h2 className="features-title">FEATURES</h2>
          <div className="feature">
            <div className="feature-icon">
              <i className="fas fa-cogs"></i>
            </div>
            <h2 className="feature-title"></h2>
            <div id="h-1" className="feature-title">
              <img src={images_1} alt="image-1" />
            </div>
            <p className="feature-description">
              Intuitive, clean and simple way of adding daily expenses and
              incomes. main screen calendar that gives a quick monthly overview
              over all transactions . Our app features a user-friendly interface
              designed for easy navigation and input. Adding your daily expenses
              and incomes is a breeze, even for those who are not tech-savvy.
              Easily categorize your expenses and incomes into predefined
              categories, making it simple to track where your money is going
              and coming from.
            </p>
          </div>
          <div className="feature">
            <div className="feature-icon">
              <i className="fas fa-chart-pie"></i>
            </div>
            <h2 className="feature-title"></h2>
            <div id="section2" className="feature-title">
              <img src={images_2} alt="image-1" />
            </div>
            <p className="feature-description">
              Your data is always safe wherever your are and what ever device
              you are using. For an added layer of security, enable two-factor
              authentication to ensure that only you can access your financial
              data. Set a strong password to further safeguard your financial
              information. Our app encourages best practices for password
              security. We maintain a transparent privacy policy that outlines
              how your data is handled and protected. You can review it at any
              time.
            </p>
          </div>
          <div className="feature">
            <div className="feature-icon">
              <i className="fas fa-lock"></i>
            </div>
            <h2 className="feature-title"></h2>
            <div id="section2" className="feature-title">
              <img src={images_3} alt="image-1" />
            </div>
            <p className="feature-description">
              A compact yet comprehensive list of expense and income categories.
              Add or edit categories, if defaults don’t work for you. Create,
              edit, or delete categories at any time. Our app adapts to your
              evolving financial priorities. Keep your finances organized
              effortlessly with a carefully curated list of default categories.
              Create categories to monitor your progress toward achieving your
              financial goals, whether it's saving for a home or planning for
              retirement.
            </p>
          </div>
          <div className="feature">
            <div className="feature-icon">
              <i className="fas fa-lock"></i>
            </div>
            <h2 className="feature-title"></h2>
            <div id="section2" className="feature-title">
              <img src={images_4} alt="image-1" />
            </div>
            <p className="feature-description">
              Set reminders for future inflow or outflow transactions and
              recurrent payments never miss another deadline. Create categories
              to monitor your progress toward achieving your financial goals,
              whether it's saving for a home or planning for retirement.Plan
              your financial future with ease by scheduling reminders for
              upcoming income and expense transactions.
            </p>
          </div>
        </div>
      </div>
      <div className="features-cont_1" id="h-2">
        <div className="features-container_1">
          <h2 className="features-heading-p3">ABOUT</h2>
          <h1 className="features-heading"></h1>
          <div className="features-row">
            <div className="feature">
              <div className="feature-icon">
                <i className="fas fa-cogs"></i>
              </div>
              <h2 className="feature-title"></h2>
              <p className="feature-description_1">
                Very simple, intuitive and straight forward
              </p>
            </div>
            <div className="feature">
              <div className="feature-icon">
                <i className="fas fa-chart-pie"></i>
              </div>
              <h2 className="feature-title"></h2>
              <p className="feature-description_2">
                If you are looking for a complicated money management
                application that promises you to do everything you ever wanted,
                then you have to keep looking because this app is not for you.
                This expense tracker & manager promises to be a very simple,
                intuitive and straight forward mobile application that helps and
                assists you to track all your expenses, manage your
                budgets/costs and organize your spending, enabling you to make
                savings and order in your financial life. Dedicated in helping
                you manage your financial life, easily, efficiently and with no
                complications
              </p>
            </div>
            <div className="feature">
              <div className="feature-icon">
                <i className="fas fa-lock"></i>
              </div>
              <h2 className="feature-title"></h2>
              <div id="h-2" className="feature-title_1">
                <img src={images_5} alt="image-1" />
              </div>
              <p className="feature-description"></p>
            </div>
          </div>
        </div>
      </div>
      <div className="pagealign" id="pa">
        <section className="faq p-4">
          <h2>Frequently Asked Questions</h2>
          <div className="faq-item">
            <h3>Q: How do I create an account?</h3>
            <p>
              A: To create an account, click the "Sign Up" button and follow the
              on-screen instructions to provide your information.
            </p>
          </div>
          <div className="faq-item">
            <h3>Q: Is my financial data secure?</h3>
            <p>
              A: Yes, we take security seriously. Your financial data is
              encrypted and protected, and we follow best practices to ensure
              its safety.
            </p>
          </div>
        </section>

        <footer className="footer">
          <div className="footer-links">
            <a href="#pa">
              <i class="bi bi-house-add-fill"></i>
            </a>
            <a href="#h-1">
              <i class="bi bi-list-task"></i>
            </a>
            <a href="#h-2">
              <i class="bi bi-info-square-fill"></i>
            </a>
            <a href="https://www.facebook.com/">
              <i class="bi bi-facebook"></i>
            </a>
            <a href="https://twitter.com/">
              <i class="bi bi-twitter-x"></i>
            </a>
            <a href="https://www.instagram.com/">
              <i class="bi bi-instagram"></i>
            </a>
            <a href="https://www.linkedin.com/">
              <i class="bi bi-linkedin"></i>
            </a>
          </div>
        </footer>
      </div>
    </div>
  );
};

export default Home;
