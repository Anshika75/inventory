import axios from 'axios';
import React, { useState } from 'react';
import KcareLogo from '/Images/Common/Logo.png';
import RightPanel from '/Images/Common/right.png';

function SignupPage() {
  // Form state

  const [isSubmitting, setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    contactNumber: '',
    password: '',
    confirmPassword: ''
  });
  



  // Password visibility state
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  // Error state
  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
    contactNumber: '',
    password: '',

  });

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    // Clear error when user starts typing
    if (errors[name]) {
      setErrors(prev => ({
        ...prev,
        [name]: ''
      }));
    }
  };

  // Toggle password visibility
  const togglePasswordVisibility = (field) => {
    if (field === 'password') {
      setShowPassword(!showPassword);
    } else {
      setShowConfirmPassword(!showConfirmPassword);
    }
  };

  // SVG Icons
  const EyeIcon = () => (
    <svg 
      xmlns="http://www.w3.org/2000/svg" 
      width="20" 
      height="20" 
      viewBox="0 0 24 24" 
      fill="none" 
      stroke="currentColor" 
      strokeWidth="2" 
      strokeLinecap="round" 
      strokeLinejoin="round"
    >
      <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
      <circle cx="12" cy="12" r="3"/>
    </svg>
  );

  const EyeOffIcon = () => (
    <svg 
      xmlns="http://www.w3.org/2000/svg" 
      width="20" 
      height="20" 
      viewBox="0 0 24 24" 
      fill="none" 
      stroke="currentColor" 
      strokeWidth="2" 
      strokeLinecap="round" 
      strokeLinejoin="round"
    >
      <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
      <line x1="1" y1="1" x2="23" y2="23"/>
    </svg>
  );

  // Validate form
  const validateForm = () => {
    const newErrors = {};

    // Name validations
    if (!formData.firstName.trim()) {
      newErrors.firstName = 'First name is required';
    }
    if (!formData.lastName.trim()) {
      newErrors.lastName = 'Last name is required';
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!emailRegex.test(formData.email)) {
      newErrors.email = 'Invalid email format';
    }

    // Contact number validation
    const phoneRegex = /^\d{10}$/;
    if (!formData.contactNumber.trim()) {
      newErrors.contactNumber = 'Contact number is required';
    } else if (!phoneRegex.test(formData.contactNumber)) {
      newErrors.contactNumber = 'Invalid contact number (10 digits required)';
    }

    // Password validation
    if (!formData.password) {
      newErrors.password = 'Password is required';
    } else if (formData.password.length < 8) {
      newErrors.password = 'Password must be at least 8 characters';
    }

    // Confirm password validation
    if (!formData.confirmPassword) {
      newErrors.confirmPassword = 'Please confirm your password';
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Passwords do not match';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async(e) => {
    e.preventDefault();
    if (validateForm()) {

      setIsSubmitting(true)
      try {
        const response = await axios.post(
          "http://localhost:9090/api/v1/auth/register",
          
          
          {
            firstName: formData.firstName,
            lastName: formData.lastName,
            email: formData.email,
            // contactNumber: formData.contactNumber,
            password: formData.password,
          },
          {
            headers: {
              "Content-Type": "application/json",
              // Accept: "application/json",
            },
          }
        );

        localStorage.setItem("email", formData.email);
        // console.log(response)

        
    
       
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
        if (error.response && error.response.status === 409) {
          setErrors((prev) => ({
            ...prev,
            email: "Email already exist",
          }));
        } else {
          console.error("Error:", error);
        }
      }  finally {
        setIsSubmitting(false);
      }
    }
  };

  return (
    <div className="min-h-screen w-full flex flex-col justify-center items-center lg:flex-row">
      {/* Left Section */}
      <div className="p-6 lg:p-0 flex flex-col justify-center items-center w-full lg:w-[70%]">
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

        {/* Signup Form */}
        <div className="space-y-4 lg:space-y-8 max-w-xl">
          <div className="space-y-2">
            <h1 className="text-2xl lg:text-3xl font-semibold text-secondary">Create Account</h1>
            <p className="text-secondary font-medium">Enter your details to create your account</p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            {/* Name Row */}
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <label htmlFor="firstName" className="block text-sm font-semibold text-secondary">
                  First Name
                </label>
                <input
                  id="firstName"
                  name="firstName"
                  type="text"
                  value={formData.firstName}
                  onChange={handleChange}
                  placeholder="Enter first name"
                  className={`w-full px-3 py-2 border ${errors.firstName ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary`}
                />
                {errors.firstName && <p className="text-red-500 text-xs mt-1">{errors.firstName}</p>}
              </div>
              <div className="space-y-2">
                <label htmlFor="lastName" className="block text-sm font-semibold text-secondary">
                  Last Name
                </label>
                <input
                  id="lastName"
                  name="lastName"
                  type="text"
                  value={formData.lastName}
                  onChange={handleChange}
                  placeholder="Enter last name"
                  className={`w-full px-3 py-2 border ${errors.lastName ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary`}
                />
                {errors.lastName && <p className="text-red-500 text-xs mt-1">{errors.lastName}</p>}
              </div>
            </div>

            {/* Contact Row */}
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <label htmlFor="email" className="block text-sm font-semibold text-secondary">
                  Email Address
                </label>
                <input
                  id="email"
                  name="email"
                  type="email"
                  value={formData.email}
                  onChange={handleChange}
                  placeholder="Enter your email"
                  className={`w-full px-3 py-2 border ${errors.email ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary`}
                />
                {errors.email && <p className="text-red-500 text-xs mt-1">{errors.email}</p>}
              </div>
              <div className="space-y-2">
                <label htmlFor="contactNumber" className="block text-sm font-semibold text-secondary">
                  Contact Number
                </label>
                <input
                  id="contactNumber"
                  name="contactNumber"
                  type="tel"
                  value={formData.contactNumber}
                  onChange={handleChange}
                  placeholder="Enter contact number"
                  className={`w-full px-3 py-2 border ${errors.contactNumber ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary`}
                />
                {errors.contactNumber && <p className="text-red-500 text-xs mt-1">{errors.contactNumber}</p>}
              </div>
            </div>

            {/* Password Row */}
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <label htmlFor="password" className="block text-sm font-semibold text-secondary">
                  Password
                </label>
                <div className="relative">
                  <input
                    id="password"
                    name="password"
                    type={showPassword ? "text" : "password"}
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="Enter password"
                    className={`w-full px-3 py-2 border ${errors.password ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary pr-10`}
                  />
                  <button
                    type="button"
                    onClick={() => togglePasswordVisibility('password')}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
                    aria-label={showPassword ? "Hide password" : "Show password"}
                  >
                    {showPassword ? <EyeOffIcon /> : <EyeIcon />}
                  </button>
                </div>
                {errors.password && <p className="text-red-500 text-xs mt-1">{errors.password}</p>}
              </div>
              <div className="space-y-2">
                <label htmlFor="confirmPassword" className="block text-sm font-semibold text-secondary">
                  Confirm Password
                </label>
                <div className="relative">
                  <input
                    id="confirmPassword"
                    name="confirmPassword"
                    type={showConfirmPassword ? "text" : "password"}
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    placeholder="Confirm password"
                    className={`w-full px-3 py-2 border ${errors.confirmPassword ? 'border-red-500' : 'border-formBorder'} rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary pr-10`}
                  />
                  <button
                    type="button"
                    onClick={() => togglePasswordVisibility('confirm')}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-gray-700"
                    aria-label={showConfirmPassword ? "Hide confirm password" : "Show confirm password"}
                  >
                    {showConfirmPassword ? <EyeOffIcon /> : <EyeIcon />}
                  </button>
                </div>
                {errors.confirmPassword && <p className="text-red-500 text-xs mt-1">{errors.confirmPassword}</p>}
              </div>
            </div>

            <button 
              type="submit"
              className="w-full bg-primary opacity-80 transition-all hover:opacity-100 text-white py-2 px-4 rounded-md duration-200"
            >
              Sign Up
            </button>
          </form>

          <p className="text-center lg:text-lg font-medium text-secondary">
            Already have an account?{' '}
            <a href="/login" className="text-primary opacity-80 font-semibold transition-all hover:opacity-100">
              Login
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

export default SignupPage;