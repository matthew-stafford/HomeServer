const React = require('react');
const ReactDOM = require('react-dom');
import ResourceUsage from './components/resourceUsage.jsx';
import ServicesManager from './components/servicesManager.jsx';
import CoinGeckoTicker from './components/coinGeckoTicker.jsx';

class App extends React.Component {

	constructor(props) {
		super(props);
		console.log(this.props);
	}

	render() {
		return (
			<div className="reactBody container-fluid">
				<div className="row">
					<div className="col-md-3">	
						<div className="row">
							<ResourceUsage/>							
						</div>
					</div>
					<div className="col-md-4">						
						<ServicesManager/>		
					</div>
					<div className="col-md-5">
						<CoinGeckoTicker/>
					</div>	
				</div>
			</div>		
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)