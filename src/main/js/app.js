const React = require('react');
const ReactDOM = require('react-dom');
import ResourceUsage from './components/resourceUsage.jsx';
import ServicesManager from './components/servicesManager.jsx';
import CoinGeckoTicker from './components/coinGeckoTicker.jsx';
import FileManager from './components/fileManager.jsx';

class App extends React.Component {

	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className="reactBody container-fluid">
				<div className="row">
					<div className="col-md-3">	
						<ResourceUsage/>	
					</div>
					<div className="col-md-9">
						<div className="row col-md-12" style={{height:'auto'}}>
							<div className="col-md-6">	
								<ServicesManager/>		
							</div>
							<div className="col-md-6">	
								<CoinGeckoTicker/>
							</div>
							<div className="col-md-12" style={{marginTop:'15px'}}>	
								<FileManager/>					
							</div>
						</div>
						
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