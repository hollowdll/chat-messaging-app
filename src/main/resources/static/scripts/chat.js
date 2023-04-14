// Toggle between dark and light mode
const toggleDarkMode = () => {
	if (document.documentElement.getAttribute('data-bs-theme') == 'dark') {
		document.documentElement.setAttribute('data-bs-theme', 'light')
		document.querySelectorAll(".chat-message").forEach(elem => elem.classList.toggle("chat-message-dark"));
	}
	else {
		document.documentElement.setAttribute('data-bs-theme', 'dark')
		document.querySelectorAll(".chat-message").forEach(elem => elem.classList.toggle("chat-message-dark"));
	}
}