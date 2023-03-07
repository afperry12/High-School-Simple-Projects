import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import pandas as pd
import statsmodels.formula.api as smf

# df = pd.read_csv (r'C:\Users\arthu\Downloads\IDD_27092021225854053.csv')
# print (df)
# print(df.columns.tolist())
# length = (len(df.index))
# current = 0
# count = 0
# x=[]
# y=[]
# while current < length:
#     value = df["Country"].iloc[current]
#     secondValue = df["MEASURE"].iloc[current]
#     thirdValue = df["Measure"].iloc[current]
#     if value == "Turkey" and secondValue == "GINI" and thirdValue == "Gini (disposable income, post taxes and transfers)":
#         print("here")
#         yearTaken = df["Year"].iloc[current]
#         GiniCoeff = df["Value"].iloc[current]
#         x.append(yearTaken)
#         y.append(GiniCoeff)
#         print(yearTaken)
#         print(GiniCoeff)
#         count = count +1;
#     current = current + 1
#
# fig = plt.figure()
# fig.suptitle('Gini Coefficient by Year', fontsize=20)
# plt.xlabel('Year Taken', fontsize=18)
# plt.ylabel('Gini Coefficient', fontsize=16)
# print(x+y)
# print(count)
# plt.xticks(x)
# plt.scatter(x,y)
# fig.savefig('test.jpg')
# # fit = np.polyfit(x, y, 1)
# # plt.plot(yearTaken, fit[0] * GiniCoeff + fit[1], color='darkblue', linewidth=2)
# plt.show()

#above here analyzes historical gini coefficient data from Turkey.

# current = 0
# while current < length:
#     value = df["Country, in 2018"].iloc[current]
#     print(value)
#     twenty = round(df["% total income 20"].iloc[current]/100,3)
#     print(twenty)
#     fourty = round(df["% total income 40"].iloc[current]/100,3)
#     print(fourty)
#     sixty = round(df["% total income 60"].iloc[current]/100,3)
#     print(sixty)
#     eighty = round(df["% total income 80"].iloc[current]/100,3)
#     print(eighty)
#     onehundred = round(df["% total income 100"].iloc[current]/100,3)
#     print(onehundred)
#     # xvaluess = np.array([0.2,0.4,0.6,0.8,1])
#     # yvaluess = np.array([twenty,(twenty+fourty),(twenty+fourty+sixty),(twenty+fourty+sixty+eighty),(twenty+fourty+sixty+eighty+onehundred)])
#     # degree = 5
#     # df = pd.DataFrame(columns=['y', 'x'])
#     # df['x'] = xvaluess
#     # df['y'] = yvaluess
#     # weights = np.polyfit(xvaluess, yvaluess, degree)
#     # model = np.poly1d(weights)
#     # results = smf.ols(formula='y ~ model(x)', data=df).fit()
#     # print("here")
#     # print(results)
#
#
#
#     xvaluess = np.array([0.2,0.4,0.6,0.8,1]).reshape((-1, 1))
#     yvaluess = np.array([twenty,(twenty+fourty),(twenty+fourty+sixty),(twenty+fourty+sixty+eighty),(twenty+fourty+sixty+eighty+onehundred)])
#     transformer = PolynomialFeatures(degree=5, include_bias=False)
#     transformer.fit(xvaluess)
#     x_ = transformer.transform(xvaluess)
#     x_ = PolynomialFeatures(degree=5, include_bias=False).fit_transform(xvaluess)
#     model = LinearRegression().fit(x_, yvaluess)
#     r_sq = model.score(x_, yvaluess)
#     print('coefficient of determination:', r_sq)
#     print('intercept:', model.intercept_)
#     print('coefficients:', model.coef_)
#     # mymodel = np.poly1d(np.polyfit(xvaluess, yvaluess, 5))
#     # myline = np.linspace(0, 1)
#     # print(mymodel.deriv())
#     # print(mymodel(myline))
#     # plt.scatter(xvaluess, yvaluess)
#     # plt.plot(myline, mymodel(myline))
#     plt.show()
#     current=current+1

#
# poly = PolynomialFeatures(degree=5)
# x_poly = poly.fit_transform(x_train)
# poly_reg = LinearRegression()
# poly_reg.fit(x_poly, y_train)
#
fig2 = plt.figure()
fig2.suptitle('Gini Coefficient of Turkey in 2018', fontsize=20)
x=0
totalArea=0
while x<=1:
    x=x+.001
    print("x-value"+str(x))
    # Turkey:
    y = 4.29687 * x ** 5 - 8.02083 * x ** 4 + 5.26562 * x ** 3 - 0.866667 * x ** 2 + 0.325 * x
    # Belgium:
    # y=2.21354*math.pow(x,5)-5.07812*math.pow(x,4)+4.54687*math.pow(x,3)-2.05937*math.pow(x,2)+0.722083*x
    y3=x
    print("y-value"+str(y))
    rectArea = .001*(y3-y)
    # HerrNacker reminded me I needed to multiple y by the constant x value for width of each rectangle
    print("rect area: "+str(rectArea))
    totalArea=rectArea+totalArea
    print("total area: "+str(totalArea))
    y2 = x
    point1 = [x, y]
    point2 = [x, y2]

    xvalues= [point1[0], point2[0]]
    yvalues= [point1[1], point2[1]]
    plt.scatter(xvalues, yvalues, s=100, color="yellow")
    plt.plot(xvalues, yvalues, color="cyan")
x = np.arange(0, 1,.00001)
# Turkey:
y = 4.29687*x**5-8.02083*x**4+5.26562*x**3-0.866667*x**2+0.325*x
# Belgium:
# y = 2.21354*x**5-5.07812*x**4+4.54687*x**3-2.05937*x**2+0.722083*x
plt.plot(x,y,color="red")
x = np.linspace(0, 1).astype(int)
y = x**2
plt.plot(x,y,color="green")
plt.xlabel("Cumulative Percent of Population")
plt.ylabel("Cumulative Percent of Wealth")
print("The sum is", totalArea)
totalArea=totalArea*2
print("Last number was out of .5 because 1*1/2=.5 making entire area of triangle with balanced linear equation only.5. Multiply by 2 to get out of 1: "+str(totalArea))
totalArea=totalArea*100
print("Last number was decimal so convert to percent by multiplying it by 100: "+str(totalArea))
x = [.2, .4, .6, .8, 1]
# Turkey:
y = [.061, .167, .315, .524, 1]
# Belgium:
# y = [.091, .234, .422, .655, 1]


plt.xlabel('Cumulative Percentage of Population', fontsize=18)
plt.ylabel('Cumulative Percentage of Wealth', fontsize=16)
plt.scatter(x, y, s=100)
fig2.savefig('ginicoeffturkey')
plt.show()