#!/bin/bash

echo "🚀 GitHub Upload Guide for Spring Boot Demo Application"
echo "========================================================"
echo ""
echo "This script will guide you through uploading your code to GitHub."
echo ""

# Check if git is configured
if ! git config user.name > /dev/null 2>&1; then
    echo "❌ Git is not configured with your username"
    echo "Please run: git config --global user.name 'Your Name'"
    exit 1
fi

if ! git config user.email > /dev/null 2>&1; then
    echo "❌ Git is not configured with your email"
    echo "Please run: git config --global user.email 'your.email@example.com'"
    exit 1
fi

echo "✅ Git is configured:"
echo "   Username: $(git config user.name)"
echo "   Email: $(git config user.email)"
echo ""

echo "📋 Steps to upload your code to GitHub:"
echo ""
echo "1. Go to https://github.com and sign in to your account"
echo "2. Click the '+' icon → 'New repository'"
echo "3. Repository name: demo (or your preferred name)"
echo "4. Choose Public or Private"
echo "5. ⚠️ DO NOT check 'Initialize this repository with a README'"
echo "6. Click 'Create repository'"
echo ""

echo "After creating the repository on GitHub, copy the repository URL."
echo "It will look like: https://github.com/YOUR_USERNAME/demo.git"
echo ""

echo "Then run these commands in your terminal:"
echo ""
echo "git remote add origin https://github.com/YOUR_USERNAME/demo.git"
echo "git branch -M main"
echo "git push -u origin main"
echo ""

echo "💡 Alternative: Use GitHub Desktop"
echo "- Download from: https://desktop.github.com/"
echo "- Import your existing repository from: $(pwd)"
echo "- Create repository on GitHub through the app"
echo "- Push to GitHub"
echo ""

echo "🎯 Your repository will contain:"
echo "- Spring Boot application with Swagger documentation"
echo "- Palindrome checker API endpoints"
echo "- Login system with Thymeleaf templates"
echo "- Complete README with setup instructions"
echo ""

echo "🔗 After upload, access your application:"
echo "- Swagger UI: http://localhost:8081/swagger-ui/index.html"
echo "- Login Page: http://localhost:8081/login"
echo "- API Endpoints: http://localhost:8081/api/"
echo ""

echo "✨ Ready to proceed? Follow the steps above!"