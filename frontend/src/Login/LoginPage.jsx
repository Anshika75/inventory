import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import KcareLogo from '/Images/Common/Logo.png';
import RightPanel from '/Images/Common/right.png';

  
function LoginPage() {

  const navigate= useNavigate();

  const [formData, setFormData] = useState({
      email: '',
      password: '',
    });

    const [errors, setErrors] = useState({
        email: '',
        password: '',
    
      });

  const validateForm = () => {
    const newErrors = {};


    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!emailRegex.test(formData.email)) {
      newErrors.email = 'Invalid email format';
    }

    // Password validation
    if (!formData.password) {
      newErrors.password = 'Password is required';
    } else if (formData.password.length < 8) {
      newErrors.password = 'Password must be at least 8 characters';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async(e) => {
    e.preventDefault();
    if (validateForm()) {

      try {
        const response = await axios.post(
          "http://localhost:9090/api/v1/auth/login",
          
          {
            email: formData.email,
            password: formData.password,
          },
          {
            headers: {
              "Content-Type": "application/json",
              // Accept: "application/json",
            },
          }
        );

        if(response.status=200){
          navigate("/product")

        }
       
      }catch (error) {

        if (error.response && error.response.data && error.response.data.validationErrors) {
          const validationErrors = error.response.data.validationErrors;
          const newErrors = {};
          validationErrors.forEach((error) => {
            const [field, message] = error.split(": ");
            newErrors[field] = message;
          });
          setErrors(newErrors);
        } 
        else if(error.response.data.error=== `401 UNAUTHORIZED \"Wrong Email \"` && error.response.status === 401) {
          setErrors((prev) => ({
            ...prev,
            email: "wrong email",
          }));
          console.log("wrong email")
        } 
        else if(error.response.data.error=== `401 UNAUTHORIZED \"Wrong Password\"` && error.response.status === 401) {
          setErrors((prev) => ({
            ...prev,
            password: "wrong password",
          }));
          console.log("wrong password")
        } 
        
        else if(error.response.data.businessErrorDescription=== "Account Not Verified" && error.response.status === 403) {
          navigate("/verify-email")
        } 
        else {
          console.error("Error:", error);
          setErrors((prev) => ({
            ...prev,
            general: "An internal error occurred. Please contact the admin.",
          }));
        }
      }  
    }
  };



  return (
    <div className="min-h-screen w-full flex flex-col justify-center items-center lg:flex-row">
      {/* Left Section */}
      <div className=" p-6 lg:p-0 flex flex-col justify-center items-center w-full lg:w-[70%]">
        {/* Logo */}
        <div className="mb-12 w-full fixed top-4 left-4">
          <a href="/" className="flex items-center">
            <img
              src={KcareLogo}
              alt="KCARE Logo"
              className="h-10 w-auto"
            />
          </a>
        </div>

        {/* Login Form */}
        <div className="space-y-4 lg:space-y-8 max-w-xl">
          <div className="space-y-2">
            <h1 className="text-2xl lg:text-3xl font-semibold text-secondary">Welcome back!</h1>
            <p className="text-secondary font-medium">Enter your Credentials to access your account</p>
          </div>

          <form className="space-y-6" onSubmit={handleSubmit}>
            <div className="space-y-2">
              <label htmlFor="email" className="block text-sm font-semibold text-secondary">
                Email address
              </label>
              <input
                id="email"
                type="email"
                placeholder="Enter your email"
                className="w-full px-3 py-2 border border-formBorder rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary"
                required value={formData.email} onChange={(e)=>{

                  setFormData((currentState)=>({...currentState,email:e.target.value}))
                  
                }}
              />
            </div>

            <div className="space-y-2">
              <div className="flex items-center justify-between">
                <label htmlFor="password" className="block text-sm font-medium text-secondary">
                  Password
                </label>
                <a 
                  href="/forgot-password"
                  className="text-sm text-primary transition-all hover:text-primary"
                >
                  forgot password?
                </a>
              </div>
              <input
                id="password"
                type="password"
                placeholder="Enter your password"
                className="w-full px-3 py-2 border border-formBorder rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary"
                required value={formData.password} onChange={(e)=>{
                  setFormData((currentState)=>({...currentState,password:e.target.value}))
                }}
              />
            </div>

            <div className="flex items-center space-x-2">
              <input
                type="checkbox"
                id="remember"
                className="h-4 w-4 text-primary focus:ring-primary border-formBorder rounded"
              />
              <label
                htmlFor="remember"
                className="text-sm text-secondary"
              >
                Remember for 30 days
              </label>
            </div>

            <button 
              type="submit" 
              className="w-full bg-primary opacity-80 transition-all hover:opacity-100 text-white py-2 px-4 rounded-md duration-200"
            >
              Login
            </button>
          </form>

          <p className="text-center lg:text-lg font-medium text-secondary">
            Not an admin? Login as{' '}
            <a href="/department-head" className="text-primary opacity-80 font-semibold transition-all hover:opacity-100">
              Department Head
            </a>
          </p>
        </div>
      </div>

      {/* Right Section - Image */}
      <div className="hidden lg:block w-[30%] relative h-screen">
        <div className="inset-0 bg-primary w-full h-full">
          <img
            src={RightPanel}
            alt="Medical professionals in operating room"
            className="w-full h-full object-cover opacity-80 mix-blend-overlay"
          />
        </div>
      </div>
    </div>
  );
}

export default LoginPage;

