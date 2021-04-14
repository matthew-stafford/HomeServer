const React = require('react');
const ReactDOM = require('react-dom');
import ProgressBar from './components/progressBar.jsx';

class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div id="serverStatus">
				<div className="serverStatusSeperator">
					CPU
					<ProgressBar percentage="80" />
				</div>
				<div className="serverStatusSeperator">
					RAM
					<ProgressBar percentage="61" />
				</div>
				<div id="settingsDropDown">
					Settings
				</div>
			</div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)