const React = require('react');
const ReactDOM = require('react-dom');
import ResourceUsage from './components/resourceUsage.jsx';

class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className="reactBody">
				<ResourceUsage/>
			</div>		
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)