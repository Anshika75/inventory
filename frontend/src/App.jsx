import { Navigate, Route, BrowserRouter as Router, Routes } from 'react-router-dom'
import LoginPage from './Login/LoginPage'
import OtpVerification from './OTPVerification/OTPVerification'
import ProductMainPage from './ProductMainPage/MainPage'
import SignupPage from './SignUp/SignUpPage'

// Protected Route wrapper component
const ProtectedRoute = ({ children }) => {
  // You can replace this with your actual auth check
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
  
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return children;
};

function App() {
  return (
    <Router>
      <Routes>
        {/* Public Routes */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/verify-email" element={<OtpVerification />} />
        <Route path='/product' element={<ProductMainPage/>} />
        
        {/* Protected Routes */}
        {/* <Route path="/" element={
          <ProtectedRoute>
            <MainPage />
          </ProtectedRoute>
        } /> */}
        {/* <Route path="/product/" element={
          <ProtectedRoute>
            <ProductDetailPage />
          </ProtectedRoute>
        } /> */}

        {/* Redirect to login for unknown routes */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </Router>
  );
}

export default App;