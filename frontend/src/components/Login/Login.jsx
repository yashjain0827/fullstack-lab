import React, { useState } from "react";
import "./Login.css";

export default function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [forgotEmail, setForgotEmail] = useState("");
  const [errors, setErrors] = useState({});
  const [forgotErrors, setForgotErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isForgotLoading, setIsForgotLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [showForgotModal, setShowForgotModal] = useState(false);
  const [forgotSuccess, setForgotSuccess] = useState(false);

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    if (errors[name]) {
      setErrors((prev) => ({ ...prev, [name]: "" }));
    }
  };

  // Handle forgot email change
  const handleForgotChange = (e) => {
    setForgotEmail(e.target.value);
    if (forgotErrors.email) {
      setForgotErrors({ email: "" });
    }
  };

  // Validate login form
  const validateForm = () => {
    const newErrors = {};

    if (!formData.email) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "Email is invalid";
    }

    if (!formData.password) {
      newErrors.password = "Password is required";
    } else if (formData.password.length < 6) {
      newErrors.password = "Password must be at least 6 characters";
    }

    return newErrors;
  };

  // Validate forgot password form
  const validateForgotForm = () => {
    const newErrors = {};

    if (!forgotEmail) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(forgotEmail)) {
      newErrors.email = "Email is invalid";
    }

    return newErrors;
  };

  // Handle login submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const newErrors = validateForm();
    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setIsLoading(true);

    try {
      await new Promise((resolve) => setTimeout(resolve, 1500));
      console.log("Login attempt:", formData);
      alert("Login successful!");
      setFormData({ email: "", password: "" });
    } catch (error) {
      console.error("Login error:", error);
      setErrors({ general: "Login failed. Please try again." });
    } finally {
      setIsLoading(false);
    }
  };

  // Handle forgot password submission
  const handleForgotSubmit = async (e) => {
    e.preventDefault();

    const newErrors = validateForgotForm();
    if (Object.keys(newErrors).length > 0) {
      setForgotErrors(newErrors);
      return;
    }

    setIsForgotLoading(true);

    try {
      await new Promise((resolve) => setTimeout(resolve, 1500));
      console.log("Password reset requested for:", forgotEmail);
      setForgotSuccess(true);
    } catch (error) {
      console.error("Forgot password error:", error);
      setForgotErrors({ general: "Something went wrong. Please try again." });
    } finally {
      setIsForgotLoading(false);
    }
  };

  // Close forgot modal and reset
  const closeForgotModal = () => {
    setShowForgotModal(false);
    setForgotEmail("");
    setForgotErrors({});
    setForgotSuccess(false);
  };

  return (
    <div className="login-page">
      {/* Left Side - Branding */}
      <div className="login-branding">
        <div className="branding-content">
          <div className="brand-logo">◆</div>
          <h1>Welcome Back</h1>
          <p>
            Sign in to continue your journey with us. We're glad to see you
            again.
          </p>
          <div className="branding-features">
            <div className="feature">
              <span className="feature-icon">✓</span>
              <span>Secure authentication</span>
            </div>
            <div className="feature">
              <span className="feature-icon">✓</span>
              <span>Fast and reliable</span>
            </div>
            <div className="feature">
              <span className="feature-icon">✓</span>
              <span>24/7 support</span>
            </div>
          </div>
        </div>
      </div>

      {/* Right Side - Login Form */}
      <div className="login-form-side">
        <div className="login-form-wrapper">
          <div className="login-header">
            <h2>Sign In</h2>
            <p>Enter your credentials to access your account</p>
          </div>

          {errors.general && (
            <div className="error-banner">{errors.general}</div>
          )}

          <form onSubmit={handleSubmit} className="login-form">
            {/* Email Field */}
            <div className="form-group">
              <label htmlFor="email">Email Address</label>
              <div className="input-wrapper">
                <span className="input-icon">✉</span>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  placeholder="you@example.com"
                  className={errors.email ? "error" : ""}
                  disabled={isLoading}
                />
              </div>
              {errors.email && (
                <span className="error-message">{errors.email}</span>
              )}
            </div>

            {/* Password Field */}
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <div className="input-wrapper">
                <span className="input-icon">🔒</span>
                <input
                  type={showPassword ? "text" : "password"}
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  placeholder="Enter your password"
                  className={errors.password ? "error" : ""}
                  disabled={isLoading}
                />
                <button
                  type="button"
                  className="password-toggle"
                  onClick={() => setShowPassword(!showPassword)}
                  tabIndex="-1"
                  aria-label={showPassword ? "Hide password" : "Show password"}
                >
                  {showPassword ? "👁" : "👁‍🗨"}
                </button>
              </div>
              {errors.password && (
                <span className="error-message">{errors.password}</span>
              )}
            </div>

            {/* Form Options */}
            <div className="form-options">
              <label className="checkbox-label">
                <input type="checkbox" />
                <span>Remember me</span>
              </label>
              <button
                type="button"
                className="forgot-link"
                onClick={() => setShowForgotModal(true)}
              >
                Forgot Password?
              </button>
            </div>

            {/* Submit Button */}
            <button
              type="submit"
              className="submit-button"
              disabled={isLoading}
            >
              {isLoading ? (
                <>
                  <span className="loading-spinner"></span>
                  <span>Signing in...</span>
                </>
              ) : (
                "Sign In"
              )}
            </button>
          </form>

          <div className="login-footer">
            <p>
              Don't have an account? <a href="/register">Create one</a>
            </p>
          </div>
        </div>
      </div>

      {/* Forgot Password Modal */}
      {showForgotModal && (
        <div className="modal-overlay" onClick={closeForgotModal}>
          <div
            className="modal-content"
            onClick={(e) => e.stopPropagation()}
          >
            <button
              className="modal-close"
              onClick={closeForgotModal}
              aria-label="Close"
            >
              ✕
            </button>

            {!forgotSuccess ? (
              <>
                <div className="modal-header">
                  <div className="modal-icon">🔑</div>
                  <h3>Forgot Password?</h3>
                  <p>
                    No worries! Enter your email address and we'll send you
                    instructions to reset your password.
                  </p>
                </div>

                {forgotErrors.general && (
                  <div className="error-banner">{forgotErrors.general}</div>
                )}

                <form onSubmit={handleForgotSubmit} className="modal-form">
                  <div className="form-group">
                    <label htmlFor="forgotEmail">Email Address</label>
                    <div className="input-wrapper">
                      <span className="input-icon">✉</span>
                      <input
                        type="email"
                        id="forgotEmail"
                        name="forgotEmail"
                        value={forgotEmail}
                        onChange={handleForgotChange}
                        placeholder="you@example.com"
                        className={forgotErrors.email ? "error" : ""}
                        disabled={isForgotLoading}
                        autoFocus
                      />
                    </div>
                    {forgotErrors.email && (
                      <span className="error-message">
                        {forgotErrors.email}
                      </span>
                    )}
                  </div>

                  <button
                    type="submit"
                    className="submit-button"
                    disabled={isForgotLoading}
                  >
                    {isForgotLoading ? (
                      <>
                        <span className="loading-spinner"></span>
                        <span>Sending...</span>
                      </>
                    ) : (
                      "Send Reset Link"
                    )}
                  </button>
                </form>
              </>
            ) : (
              <div className="modal-success">
                <div className="success-icon">✓</div>
                <h3>Check Your Email</h3>
                <p>
                  We've sent a password reset link to{" "}
                  <strong>{forgotEmail}</strong>. Please check your inbox and
                  follow the instructions.
                </p>
                <button
                  className="submit-button"
                  onClick={closeForgotModal}
                >
                  Got it
                </button>
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}