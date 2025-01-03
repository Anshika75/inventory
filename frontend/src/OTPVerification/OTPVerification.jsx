import axios from "axios";
import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from "react-router-dom";
import KcareLogo from '/Images/Common/Logo.png';
import RightPanel from '/Images/Common/right.png';

function OtpVerification() {
  const [otp, setOtp] = useState(['', '', '', '', '', '']);
  const [error, setError] = useState('');
  const inputRefs = useRef([]);
  const navigate= useNavigate();

  // Initialize refs array
  useEffect(() => {
    inputRefs.current = inputRefs.current.slice(0, 6);
  }, []);

  const handleChange = (index, value) => {
    // Allow only numbers
    if (!/^\d*$/.test(value)) return;

    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);
    
    // Clear error when user starts typing
    if (error) setError('');

    // Move to next input if value is entered
    if (value !== '' && index < 5) {
      inputRefs.current[index + 1].focus();
    }
  };

  const handleKeyDown = (index, e) => {
    // Handle backspace
    if (e.key === 'Backspace') {
      if (index > 0 && otp[index] === '') {
        const newOtp = [...otp];
        newOtp[index - 1] = '';
        setOtp(newOtp);
        inputRefs.current[index - 1].focus();
      } else if (otp[index] !== '') {
        const newOtp = [...otp];
        newOtp[index] = '';
        setOtp(newOtp);
      }
    }
  };

  const handlePaste = (e) => {
    e.preventDefault();
    const pastedData = e.clipboardData.getData('text').slice(0, 6);
    
    // Check if pasted content contains only numbers
    if (!/^\d+$/.test(pastedData)) {
      setError('Please paste numbers only');
      return;
    }

    const newOtp = [...otp];
    pastedData.split('').forEach((char, index) => {
      if (index < 6) newOtp[index] = char;
    });
    setOtp(newOtp);

    // Focus last input or next empty input
    const lastFilledIndex = newOtp.findIndex(val => val === '');
    const focusIndex = lastFilledIndex === -1 ? 5 : lastFilledIndex;
    inputRefs.current[focusIndex].focus();
  };

  const handleSubmit = async(e) => {
    e.preventDefault();
    
    // Validate all digits are entered
    if (otp.some(digit => digit === '')) {
      setError('Please enter all digits');
      return;
    }
    try {
      const response = await axios.post('http://localhost:9090/api/v1/auth/otp-verificiation', 
      { "otp": otp.join(''),
        "email": localStorage.getItem("email")
       }, 
      {
        headers: {
          'Content-Type': 'application/json',
        }
      });


      if(response.status===200 && response.data.status==="ACCEPTED"){
        localStorage.removeItem("email")
        navigate("/login")
        
      }
      // console.log(response.data);
      // Handle success response
    } catch (error) {

      if(error.response.status===404 && error.response.data.businessErrorDescription=== "Invalid Otp"){
        setError(error.response.data.businessErrorDescription);
      }
      else{
        console.error('Error:', error);
      }

      
      
    }
    

    // Handle OTP submission
    // console.log('OTP Submitted:', otp.join(''));
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

        {/* OTP Verification Form */}
        <div className="space-y-4 lg:space-y-8 max-w-xl">
          <div className="space-y-2">
            <h1 className="text-2xl lg:text-3xl font-semibold text-secondary">Verify Your Email</h1>
            <p className="text-secondary font-medium">
              Please enter the 6 digit verification code sent to your registered email id:{' '}
              <span className="font-semibold">abc@gmail.com</span>
            </p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-8">
            <div className="flex justify-center space-x-4">
              {otp.map((digit, index) => (
                <input
                  key={index}
                  ref={el => inputRefs.current[index] = el}
                  type="text"
                  inputMode="numeric"
                  maxLength={1}
                  value={digit}
                  onChange={(e) => handleChange(index, e.target.value)}
                  onKeyDown={(e) => handleKeyDown(index, e)}
                  onPaste={handlePaste}
                  className={`w-12 h-12 text-center text-2xl font-semibold border ${
                    error ? 'border-red-500' : 'border-formBorder'
                  } rounded-md focus:outline-none focus:ring-1 focus:ring-primary focus:border-primary`}
                  aria-label={`Digit ${index + 1} of OTP`}
                />
              ))}
            </div>

            {error && (
              <p className="text-red-500 text-sm text-center">{error}</p>
            )}

            <button 
              type="submit"
              className="w-full bg-primary opacity-80 transition-all hover:opacity-100 text-white py-2 px-4 rounded-md duration-200"
            >
              Verify Email
            </button>
          </form>

          <div className="text-center space-y-4">
            <p className="text-secondary">
              Didn't receive the code?{' '}
              <button 
                type="button"
                className="text-primary opacity-80 font-semibold transition-all hover:opacity-100"
                onClick={() => console.log('Resend OTP')}
              >
                Resend
              </button>
            </p>
            <p className="text-center lg:text-lg font-medium text-secondary">
              Back to{' '}
              <a href="/login" className="text-primary opacity-80 font-semibold transition-all hover:opacity-100">
                Login
              </a>
            </p>
          </div>
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

export default OtpVerification;