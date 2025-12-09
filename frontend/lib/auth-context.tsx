"use client";

import {
  createContext,
  useContext,
  useState,
  useEffect,
  ReactNode,
} from "react";

interface AuthContextType {
  token: string | null;
  email: string | null;
  isLoading: boolean;
  error: string | null;
  login: (email: string, password: string) => Promise<void>;
  register: (email: string, name: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(null);
  const [email, setEmail] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const savedToken = localStorage.getItem("token");
    const savedEmail = localStorage.getItem("userEmail");
    if (savedToken) {
      setToken(savedToken);
      setEmail(savedEmail);
    }
  }, []);

  const login = async (email: string, password: string) => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        credentials: "include",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        try {
          const errorData = await response.json();
          throw new Error(errorData.error || "Login failed");
        } catch (err) {
          throw new Error(err instanceof Error ? err.message : "Login failed");
        }
      }

      const data = await response.json();
      setToken(data.token);
      setEmail(email);
      localStorage.setItem("token", data.token);
      localStorage.setItem("userEmail", email);
      console.log(data);
    } catch (err) {
      const message = err instanceof Error ? err.message : "Login failed";
      setError(message);
      throw err;
    } finally {
      setIsLoading(false);
    }
  };

  const register = async (email: string, name: string, password: string) => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        credentials: "include",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, name, password }),
      });

      if (!response.ok) {
        try {
          const errorData = await response.json();
          throw new Error(errorData.error || "Registration failed");
        } catch (err) {
          throw new Error(
            err instanceof Error ? err.message : "Registration failed",
          );
        }
      }

      await login(email, password);
    } catch (err) {
      const message =
        err instanceof Error ? err.message : "Registration failed";
      setError(message);
      throw err;
    } finally {
      setIsLoading(false);
    }
  };

  const logout = () => {
    setToken(null);
    setEmail(null);
    localStorage.removeItem("token");
    localStorage.removeItem("userEmail");
  };

  return (
    <AuthContext.Provider
      value={{ token, email, isLoading, error, login, register, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return context;
}
