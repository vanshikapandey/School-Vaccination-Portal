
export default function Login() {

  return (
    <div className="flex items-center justify-center h-screen bg-gradient-to-br from-blue-100 via-white to-purple-100">
      <div className="bg-white shadow-lg rounded-xl p-10 flex flex-col items-center space-y-6 w-80">
        <h1 className="text-2xl font-semibold text-gray-800">Welcome</h1>
        <p className="text-gray-500 text-center">Login to continue to the School Vaccination Portal</p>
        <a href="http://localhost:8080/oauth2/authorization/google" className="w-full">
          <button className="w-full bg-blue-600 hover:bg-blue-700 text-white py-3 rounded-lg text-lg font-medium transition duration-200">
            Login with Google
          </button>
        </a>
      </div>
    </div>
  )
}
