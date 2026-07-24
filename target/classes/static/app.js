const API_BASE = 'http://localhost:8080/api';

// Utility to handle API calls
async function fetchApi(endpoint, method = 'GET', body = null) {
    const options = {
        method,
        headers: {
            'Content-Type': 'application/json',
        }
    };
    if (body) {
        options.body = JSON.stringify(body);
    }
    
    try {
        const response = await fetch(`${API_BASE}${endpoint}`, options);
        return await response.json();
    } catch (error) {
        console.error('API Error:', error);
        return { success: false, message: 'Server error occurred' };
    }
}

// User state management
function setUser(user) {
    localStorage.setItem('railway_user', JSON.stringify(user));
}

function getUser() {
    const userStr = localStorage.getItem('railway_user');
    return userStr ? JSON.parse(userStr) : null;
}

function logout() {
    localStorage.removeItem('railway_user');
    window.location.href = '/login.html';
}

function checkAuth(requireAdmin = false) {
    const user = getUser();
    if (!user) {
        window.location.href = '/login.html';
        return;
    }
    if (requireAdmin && user.role !== 'ADMIN') {
        window.location.href = '/user-dashboard.html';
        return;
    }
    
    // Update UI elements if present
    const userNameNode = document.getElementById('navbar-user-name');
    if (userNameNode) userNameNode.innerText = user.fullName || user.username || 'User';
}

function showAlert(message, isError = false) {
    alert(message); // Could be replaced with better toast UI
}
